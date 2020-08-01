package top.chenbn.aclservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.chenbn.aclservice.entity.RolePermission;
import top.chenbn.aclservice.mapper.RolePermissionMapper;
import top.chenbn.aclservice.service.RolePermissionService;

/**
 * 角色权限 服务实现类
 *
 * @author testjava
 * @since 2020-08-01
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService {}
