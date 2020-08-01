package top.chenbn.aclservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.chenbn.aclservice.entity.UserRole;
import top.chenbn.aclservice.mapper.UserRoleMapper;
import top.chenbn.aclservice.service.UserRoleService;

/**
 * 服务实现类
 *
 * @author testjava
 * @since 2020-08-01
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService {}
