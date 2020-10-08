package ec.common.error.handler;

import cn.hutool.core.util.StrUtil;
import ec.common.error.ErrorMessageEnum;
import ec.common.exception.ListValidException;
import ec.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zack <br>
 * @create 2020-10-11 12:04 <br>
 * @project project-ec <br>
 */
@Slf4j
public abstract class BaseValidationExceptionHandler {
  @ExceptionHandler(ValidationException.class)
  public R handleValidationException(ValidationException ex) throws Exception {
    R errorResponse = R.error(ErrorMessageEnum.BEAN_VALIDATION_ERROR);

    Throwable cause = ex.getCause();
    Map<String, Object> collect = new HashMap<>(16);

    if (cause instanceof ListValidException) {
      Map<Integer, Set<ConstraintViolation<Object>>> errors =
          ((ListValidException) cause).getErrors();

      errors.forEach(
          (i, error) -> {
            error.stream()
                .parallel()
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

    return errorResponse.put("errors", collect);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public R handleConstraintViolationException(ConstraintViolationException ex) {

    R errorResponse = R.error(ErrorMessageEnum.BEAN_VALIDATION_ERROR);
    Map<String, Object> collect =
        ex.getConstraintViolations().stream()
            .parallel()
            .collect(
                Collectors.toMap(
                    x -> StrUtil.subAfter(x.getPropertyPath().toString(), ".", false),
                    x ->
                        new HashMap<String, Object>(2) {
                          {
                            put("rejectValue", x.getInvalidValue());
                            put("message", x.getMessage());
                          }
                        },
                    (s, a) -> Arrays.asList(s, a)));

    return errorResponse.put("errors", collect);
  }

  @ExceptionHandler(BindException.class)
  public R handleBindException(BindException ex) {

    return getErrorResults(ex.getBindingResult(), ex);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public R handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

    return getErrorResults(ex.getBindingResult(), ex);
  }

  private R getErrorResults(BindingResult bindingResult, Exception ex) {

    Map<String, Object> collect =
        bindingResult.getFieldErrors().stream()
            .parallel()
            .collect(
                Collectors.toMap(
                    FieldError::getField,
                    x ->
                        new HashMap<String, Object>(2) {
                          {
                            put("rejectValue", x.getRejectedValue());
                            put("message", x.getDefaultMessage());
                          }
                        },
                    (s, a) -> Arrays.asList(s, a)));

    return R.error(ErrorMessageEnum.BEAN_VALIDATION_ERROR).put("errors", collect);
  }
}
