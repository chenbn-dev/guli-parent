package top.chenbn.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import top.chenbn.security.filter.TokenAuthenticationFilter;
import top.chenbn.security.filter.TokenLoginFilter;
import top.chenbn.security.security.DefaultPasswordEncoder;
import top.chenbn.security.security.TokenLogoutHandler;
import top.chenbn.security.security.TokenManager;
import top.chenbn.security.security.UnauthorizedEntryPoint;

/**
 * Security配置类
 *
 * @author chenbn
 * @since 2020-08-01
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {
  // 自定义查询数据库的类
  private UserDetailsService userDetailsService;
  // 生成token工具
  private TokenManager tokenManager;
  // 密码处理
  private DefaultPasswordEncoder defaultPasswordEncoder;
  // redis操作
  private RedisTemplate redisTemplate;

  @Autowired
  public TokenWebSecurityConfig(
      UserDetailsService userDetailsService,
      DefaultPasswordEncoder defaultPasswordEncoder,
      TokenManager tokenManager,
      RedisTemplate redisTemplate) {
    this.userDetailsService = userDetailsService;
    this.defaultPasswordEncoder = defaultPasswordEncoder;
    this.tokenManager = tokenManager;
    this.redisTemplate = redisTemplate;
  }

  /**
   * 配置设置
   *
   * @param http
   * @throws Exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.exceptionHandling()
        .authenticationEntryPoint(new UnauthorizedEntryPoint())
        .and()
        .csrf()
        .disable()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .logout()
        .logoutUrl("/admin/acl/index/logout")
        .addLogoutHandler(new TokenLogoutHandler(tokenManager, redisTemplate))
        .and()
        .addFilter(new TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate))
        .addFilter(
            new TokenAuthenticationFilter(authenticationManager(), tokenManager, redisTemplate))
        .httpBasic();
  }

  /**
   * 密码处理
   *
   * @param auth
   * @throws Exception
   */
  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
  }

  /**
   * 配置哪些请求不拦截
   *
   * @param web
   * @throws Exception
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    //        web.ignoring().antMatchers("/api/**",
    //                "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**"
    //               );
    web.ignoring().antMatchers("/*/**");
  }
}
