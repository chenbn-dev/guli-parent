package top.chenbn.aclservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.chenbn.aclservice.entity.User;

/**
 * 用户表 服务类
 *
 * @author testjava
 * @since 2020-08-01
 */
public interface UserService extends IService<User> {

  // 从数据库中取出用户信息
  User selectByUsername(String username);
}
