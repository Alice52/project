package ec.common.error;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author zack <br>
 * @create 2020-10-11 11:55 <br>
 * @project project-ec <br>
 */
@Slf4j
public enum ErrorMessageEnum {
  // common errors
  SYSTEM_ERROR(999999, "Internal Server Error"),
  BEAN_VALIDATION_ERROR(400400, "Validate bean property error");

  private Integer code;
  private String msg;

  ErrorMessageEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  /**
   * get ErrorMessage by error code.
   *
   * @param errorCode
   * @return ErrorMessage
   */
  public static ErrorMessageEnum getByErrorCode(final Integer errorCode) {
    return Arrays.stream(values())
        .filter(enumErrorCode -> enumErrorCode.getCode().equals(errorCode))
        .findFirst()
        .orElse(null);
  }

  /**
   * get ErrorMessage by enum name, such as UNKNOWN_EXCEPTION.
   *
   * @param enumName
   * @return ErrorMessage
   */
  public static ErrorMessageEnum getByEnumName(final String enumName) {
    Optional<ErrorMessageEnum> errorCode = getValueOf(ErrorMessageEnum.class, enumName);
    return errorCode.isPresent() ? errorCode.get() : null;
  }

  public static String getMessageByCode(final String errorCode) {
    Optional<ErrorMessageEnum> errorMessage =
        Arrays.stream(values())
            .filter(enumErrorCode -> enumErrorCode.getCode().equals(errorCode))
            .findFirst();
    return errorMessage.isPresent() ? errorMessage.get().getMsg() : null;
  }

  public static Integer getCodeByMessage(final String errorMessage) {
    Optional<ErrorMessageEnum> errorCode =
        Arrays.stream(ErrorMessageEnum.class.getEnumConstants())
            .filter(enumErrorCode -> enumErrorCode.getMsg().equals(errorMessage))
            .findFirst();
    return errorCode.isPresent() ? errorCode.get().getCode() : null;
  }

  /**
   * Convert enum to Optional, and handle IllegalArgumentException.
   *
   * @param enumType
   * @param name
   * @return Optional<Enum> or Optional.empty()
   */
  private static Optional<ErrorMessageEnum> getValueOf(
      Class<ErrorMessageEnum> enumType, String name) {
    ErrorMessageEnum enumValue;
    try {
      enumValue = Enum.valueOf(enumType, name);
    } catch (IllegalArgumentException ex) {
      log.info(
          "occurs IllegalArgumentException when get enum[{}] by enum name: {}, so return {}",
          enumType,
          name,
          Optional.empty());
      return Optional.empty();
    }
    return Optional.ofNullable(enumValue);
  }
}
