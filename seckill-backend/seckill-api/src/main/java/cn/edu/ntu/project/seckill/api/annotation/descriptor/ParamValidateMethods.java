//package cn.edu.ntu.project.seckill.api.annotation.descriptor;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import cn.edu.ntu.project.seckill.api.annotation.ParamValidate;
//import cn.hutool.core.util.StrUtil;
//
///**
// * //TODO: how to make this class work?
// *
// * @author zack <br>
// * @create 2020-06-08 22:26 <br>
// * @project seckill-backend <br>
// */
//public class ParamValidateMethods {
//
//  public static Annotation getAnnotationByMethod(Method method, Class annoClass) {
//    Annotation all[] = method.getAnnotations();
//    for (Annotation annotation : all) {
//      if (annotation.annotationType() == annoClass) {
//        return annotation;
//      }
//    }
//    return null;
//  }
//
//  public static Method getMethodByClassAndName(Class c, String methodName) {
//    Method[] methods = c.getDeclaredMethods();
//    for (Method method : methods) {
//      if (method.getName().equals(methodName)) {
//        return method;
//      }
//    }
//    return null;
//  }
//
//  public static String getGetterNameByFiledName(String fieldName) {
//    return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
//  }
//
//  /**
//   * @author JinXue 2015年7月3日 下午9:46:32 @Method: getFieldByObjectAndFileName @Description: TODO
//   *     根据对象和属性名得到 属性
//   * @param targetObj
//   * @param fileName
//   * @return
//   * @throws SecurityException
//   * @throws NoSuchMethodException
//   * @throws IllegalArgumentException
//   * @throws IllegalAccessException
//   * @throws InvocationTargetException
//   * @throws
//   */
//  public static Object getFieldByObjectAndFileName(Object targetObj, String fileName)
//      throws SecurityException, NoSuchMethodException, IllegalArgumentException,
//          IllegalAccessException, InvocationTargetException {
//    String tmp[] = fileName.split("\\.");
//    Object arg = targetObj;
//    for (int i = 0; i < tmp.length; i++) {
//      Method methdo = arg.getClass().getMethod(getGetterNameByFiledName(tmp[i]));
//      arg = methdo.invoke(arg);
//    }
//    return arg;
//  }
//
//  /**
//   * @author JinXue 2015年7月3日 下午10:25:05 @Method: validateFiled @Description: TODO 验证字段的正确性
//   * @param valiedatefiles
//   * @param request
//   * @return
//   * @throws SecurityException
//   * @throws IllegalArgumentException
//   * @throws NoSuchMethodException
//   * @throws IllegalAccessException
//   * @throws InvocationTargetException
//   * @throws Exception
//   * @throws
//   */
//  public static boolean validateFiled(ParamValidate[] valiedatefiles, HttpServletRequest request)
//      throws SecurityException, IllegalArgumentException, NoSuchMethodException,
//          IllegalAccessException, InvocationTargetException, Exception {
//    Map<String, Object> errorMaps = new HashMap<String, Object>();
//    boolean flag = true;
//    for (ParamValidate validateFiled : valiedatefiles) {
//      Object arg = null;
//      if (!"".equals(validateFiled.name())) {
//        arg = request.getParameter(validateFiled.name());
//      } else {
//        flag = false;
//        errorMaps.put(validateFiled.name(), "需验证的字段不能为空");
//      }
//      // 判断参数是否为空
//      if (validateFiled.notNull()) {
//        if (arg == null || "".equals(arg.toString())) {
//          flag = false;
//          errorMaps.put(validateFiled.name(), validateFiled.message());
//        }
//      } else { // 如果该参数能够为空，并且当参数为空时，就不用判断后面的了 ，直接返回true
//        if (arg == null) {
//          return true;
//        }
//      }
//      if (arg instanceof String) {
//        // 判断字符串最大长度
//        if (!StrUtil.isBlank(arg.toString()) && validateFiled.maxLen() > 0) {
//          if (((String) arg).length() > validateFiled.maxLen()) {
//            flag = false;
//            errorMaps.put(validateFiled.name(), validateFiled.message());
//          }
//        }
//        if (!StrUtil.isBlank(arg.toString()) && validateFiled.minLen() > 0) { // 判断字符串最小长度
//          if (((String) arg).length() < validateFiled.minLen()) {
//            flag = false;
//            errorMaps.put(validateFiled.name(), validateFiled.message());
//          }
//        }
//        if (!StrUtil.isBlank(arg.toString()) && !"".equals(validateFiled.regex())) { // 判断正则
//          if (arg instanceof String) {
//            if (!((String) arg).matches(validateFiled.regex())) {
//              flag = false;
//              errorMaps.put(validateFiled.name(), validateFiled.message());
//            }
//          } else {
//            flag = false;
//            errorMaps.put(validateFiled.name(), validateFiled.message());
//          }
//        }
//        if (!StrUtil.isBlank(arg.toString()) && !"".equals(validateFiled.dateFormat())) {
//          SimpleDateFormat sdf = new SimpleDateFormat();
//          try {
//            sdf.parse(validateFiled.dateFormat());
//          } catch (ParseException e) {
//            flag = false;
//            errorMaps.put(validateFiled.name(), validateFiled.message());
//          }
//        }
//      }
//      if (arg != null && validateFiled.maxVal() != -1) { // 判断数值最大值
//        if (Integer.parseInt(arg.toString()) > validateFiled.maxVal()) {
//          flag = false;
//          errorMaps.put(validateFiled.name(), validateFiled.message());
//        }
//      }
//      if (arg != null && validateFiled.minVal() != -1) { // 判断数值最小值
//        if (Integer.parseInt(arg.toString()) < validateFiled.minVal()) {
//          flag = false;
//          errorMaps.put(validateFiled.name(), validateFiled.message());
//        }
//      }
//    }
//    if (!flag) {
//      throw new Exception(String.valueOf(flag));
//    }
//
//    return flag;
//  }
//}
