package top.chenbn.security.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import top.chenbn.guli.common.util.MD5;

/**
 * t密码的处理方法类型
 *
 * @author chenbn
 * @since 2020-08-01
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

  public DefaultPasswordEncoder() {
    this(-1);
  }

  /** @param strength the log rounds to use, between 4 and 31 */
  public DefaultPasswordEncoder(int strength) {}

  @Override
  public String encode(CharSequence rawPassword) {
    return MD5.encrypt(rawPassword.toString());
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return encodedPassword.equals(MD5.encrypt(rawPassword.toString()));
  }
}
