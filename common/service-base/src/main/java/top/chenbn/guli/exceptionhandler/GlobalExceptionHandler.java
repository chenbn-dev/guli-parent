package top.chenbn.guli.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.chenbn.guli.common.util.ExceptionUtil;
import top.chenbn.guli.common.util.Result;

/**
 * @author chbn
 * @create 2020-06-26
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ResponseBody
  @ExceptionHandler(Exception.class) // 指定出现什么异常执行这个方法
  public Result error(Exception e) {
    e.printStackTrace();
    return Result.error().message("执行了全局异常处理......");
  }

  @ResponseBody
  @ExceptionHandler(ArithmeticException.class) // 指定出现什么异常执行这个方法
  public Result error(ArithmeticException e) {
    e.printStackTrace();
    return Result.error().message("执行了ArithmeticException异常处理......");
  }

  @ResponseBody
  @ExceptionHandler(GuliException.class) // 指定出现什么异常执行这个方法
  public Result error(GuliException e) {
    log.error(ExceptionUtil.getMessage(e));
    e.printStackTrace();
    return Result.error().code(e.getCode()).message(e.getMsg());
  }
}
