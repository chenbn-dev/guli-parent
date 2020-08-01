package top.chenbn.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import top.chenbn.guli.common.util.ResponseUtil;
import top.chenbn.guli.common.util.Result;
import top.chenbn.security.entity.SecurityUser;
import top.chenbn.security.entity.User;
import top.chenbn.security.security.TokenManager;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 登录过滤器，继承UsernamePasswordAuthenticationFilter，对用户名密码进行登录校验
 *
 * @author chenbn
 * @since 2020-08-01
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;
  private TokenManager tokenManager;
  private RedisTemplate redisTemplate;

  public TokenLoginFilter(
      AuthenticationManager authenticationManager,
      TokenManager tokenManager,
      RedisTemplate redisTemplate) {
    this.authenticationManager = authenticationManager;
    this.tokenManager = tokenManager;
    this.redisTemplate = redisTemplate;
    this.setPostOnly(false);
    this.setRequiresAuthenticationRequestMatcher(
        new AntPathRequestMatcher("/admin/acl/login", "POST"));
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
      throws AuthenticationException {
    try {
      User user = new ObjectMapper().readValue(req.getInputStream(), User.class);

      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              user.getUsername(), user.getPassword(), new ArrayList<>()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 登录成功
   *
   * @param req
   * @param res
   * @param chain
   * @param auth
   * @throws IOException
   * @throws ServletException
   */
  @Override
  protected void successfulAuthentication(
      HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth)
      throws IOException, ServletException {
    SecurityUser user = (SecurityUser) auth.getPrincipal();
    String token = tokenManager.createToken(user.getCurrentUserInfo().getUsername());
    redisTemplate
        .opsForValue()
        .set(user.getCurrentUserInfo().getUsername(), user.getPermissionValueList());

    ResponseUtil.out(res, Result.ok().data("token", token));
  }

  /**
   * 登录失败
   *
   * @param request
   * @param response
   * @param e
   * @throws IOException
   * @throws ServletException
   */
  @Override
  protected void unsuccessfulAuthentication(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
      throws IOException, ServletException {
    ResponseUtil.out(response, Result.error());
  }
}
