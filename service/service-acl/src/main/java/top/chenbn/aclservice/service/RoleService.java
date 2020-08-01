package top.chenbn.aclservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.chenbn.aclservice.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * 服务类
 *
 * @author testjava
 * @since 2020-08-01
 */
public interface RoleService extends IService<Role> {

  // 根据用户获取角色数据
  Map<String, Object> findRoleByUserId(String userId);

  // 根据用户分配角色
  void saveUserRoleRealtionShip(String userId, String[] roleId);

  List<Role> selectRoleByUserId(String id);
}
