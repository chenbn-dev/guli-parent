package top.chenbn.aclservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.chenbn.aclservice.entity.User;
import top.chenbn.aclservice.mapper.UserMapper;
import top.chenbn.aclservice.service.UserService;

/**
 * 用户表 服务实现类
 *
 * @author testjava
 * @since 2020-08-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  @Override
  public User selectByUsername(String username) {
    return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
  }
}
