package top.chenbn.guli.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.chenbn.guli.commonutil.Result;

/**
 * @author chbn
 * @create 2020-06-26 15:19
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  @ResponseBody
  @ExceptionHandler(Exception.class)
  public Result error(Exception e) {
    e.printStackTrace();
    return Result.error().message("执行了全局异常处理......");
  }
}
