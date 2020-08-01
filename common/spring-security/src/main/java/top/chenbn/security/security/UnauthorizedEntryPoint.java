package top.chenbn.security.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import top.chenbn.guli.common.util.ResponseUtil;
import top.chenbn.guli.common.util.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未授权的统一处理方式
 *
 * @author chenbn
 * @since 2020-08-01
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException, ServletException {

    ResponseUtil.out(response, Result.error());
  }
}
