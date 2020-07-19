package top.chenbn.guli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.chenbn.guli.entity.UcenterMember;
import top.chenbn.guli.entity.vo.RegisterVO;

/**
 * 会员表 服务类
 *
 * @author chenbn
 * @since 2020-07-16
 */
public interface UcenterMemberService extends IService<UcenterMember> {
  /**
   * 登录的方法
   *
   * @param member
   * @return
   */
  String login(UcenterMember member);

  /**
   * 用户注册
   *
   * @param registerVO
   */
  void register(RegisterVO registerVO);

  UcenterMember getOpenIdMember(String openid);
}
