package ec.coupon.handler;

import cn.hutool.core.util.StrUtil;
import ec.coupon.exception.ListValidException;
import ec.coupon.model.ErrorMessageEnum;
import ec.coupon.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zack <br>
 * @create 2020-10-06 12:00 <br>
 * @project project-ec <br>
 */
@Order(100)
@Slf4j
@ResponseBody
@ControllerAdvice
public class BeanValidationExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity handleValidationException(ValidationException ex) throws Exception {
    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.BEAN_VALIDATION_ERROR);

    Throwable cause = ex.getCause();
    Map<String, Object> collect = new HashMap<>(16);

    if (cause instanceof ListValidException) {
      Map<Integer, Set<ConstraintViolation<Object>>> errors =
          ((ListValidException) cause).getErrors();

      errors.forEach(
          (i, error) -> {
            error.stream()
                .forEach(
                    x ->
                        collect.put(
                            "[" + i + "]." + x.getPropertyPath().toString(),
                            new HashMap<String, Object>(2) {
                              {
                                put("rejectValue", x.getInvalidValue());
                                put("message", x.getMessage());
                              }
                            }));
          });
    } else {
      log.error(
          "validation bean error, type {}, params {}, message {}, cause {}, stack trace {}",
          ex.getClass().getTypeName(),
          ex.getClass().getTypeParameters(),
          ex.getMessage(),
          ex.getCause(),
          ex.getStackTrace());

      collect.put("message", ex.getMessage());
      collect.put("exception type", ex.getClass().getTypeName());
    }

    errorResponse.setParameters(collect);

    return DefaultExceptionHandler.buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST, ex);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public Object handleConstraintViolationException(ConstraintViolationException ex) {

    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.BEAN_VALIDATION_ERROR);
    Map<String, Object> collect =
        ex.getConstraintViolations().stream()
            .collect(
                Collectors.toMap(
                    x -> StrUtil.subAfter(x.getPropertyPath().toString(), ".", false),
                    x ->
                        new HashMap<String, Object>(2) {
                          {
                            put("rejectValue", x.getInvalidValue());
                            put("message", x.getMessage());
                          }
                        }));

    errorResponse.setParameters(collect);

    return DefaultExceptionHandler.buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST, ex);
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity handleBindException(BindException ex) {

    return getErrorResults(ex.getBindingResult(), ex);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

    return getErrorResults(ex.getBindingResult(), ex);
  }

  private ResponseEntity getErrorResults(BindingResult bindingResult, Exception ex) {
    Map<String, Object> collect =
        bindingResult.getFieldErrors().stream()
            .collect(
                Collectors.toMap(
                    FieldError::getField,
                    x ->
                        new HashMap<String, Object>(2) {
                          {
                            put("rejectValue", x.getRejectedValue());
                            put("message", x.getDefaultMessage());
                          }
                        }));

    ErrorResponse errorResponse = ErrorResponse.error(ErrorMessageEnum.BEAN_VALIDATION_ERROR);
    errorResponse.setParameters(collect);

    return DefaultExceptionHandler.buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST, ex);
  }
}
