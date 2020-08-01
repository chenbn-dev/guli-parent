package top.chenbn.aclservice.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.chenbn.aclservice.entity.User;
import top.chenbn.aclservice.service.PermissionService;
import top.chenbn.aclservice.service.UserService;
import top.chenbn.security.entity.SecurityUser;

import java.util.List;

/**
 * 自定义userDetailsService - 认证用户详情
 *
 * @author qy
 * @since 2020-08-01
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired private UserService userService;

  @Autowired private PermissionService permissionService;

  /**
   * * 根据账号获取用户信息
   *
   * @param username:
   * @return: org.springframework.security.core.userdetails.UserDetails
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // 从数据库中取出用户信息
    User user = userService.selectByUsername(username);

    // 判断用户是否存在
    if (null == user) {
      // throw new UsernameNotFoundException("用户名不存在！");
    }
    // 返回UserDetails实现类
    top.chenbn.security.entity.User curUser = new top.chenbn.security.entity.User();
    BeanUtils.copyProperties(user, curUser);

    List<String> authorities = permissionService.selectPermissionValueByUserId(user.getId());
    SecurityUser securityUser = new SecurityUser(curUser);
    securityUser.setPermissionValueList(authorities);
    return securityUser;
  }
}
