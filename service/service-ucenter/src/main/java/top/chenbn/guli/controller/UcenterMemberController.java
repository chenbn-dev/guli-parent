package top.chenbn.guli.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chenbn.guli.common.util.JwtUtils;
import top.chenbn.guli.common.util.Result;
import top.chenbn.guli.common.util.ordervo.UcenterMemberOrder;
import top.chenbn.guli.entity.UcenterMember;
import top.chenbn.guli.entity.vo.RegisterVO;
import top.chenbn.guli.service.UcenterMemberService;

import javax.servlet.http.HttpServletRequest;

/**
 * 会员表 前端控制器
 *
 * @author chenbn
 * @since 2020-07-16
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/ucenter/member")
public class UcenterMemberController {
  @Autowired private UcenterMemberService memberService;

  // 登录
  @PostMapping("/login")
  public Result loginUser(@RequestBody UcenterMember member) {
    // member对象封装手机号和密码
    // 调用service方法实现登录
    // 返回token值，使用jwt生成
    String token = memberService.login(member);
    return Result.ok().data("token", token);
  }

  // 注册
  @PostMapping("/register")
  public Result registerUser(@RequestBody RegisterVO registerVo) {
    memberService.register(registerVo);
    return Result.ok();
  }

  // 根据token获取用户信息
  @GetMapping("/getMemberInfo")
  public Result getMemberInfo(HttpServletRequest request) {
    // 调用jwt工具类的方法。根据request对象获取头信息，返回用户id
    String memberId = JwtUtils.getMemberIdByJwtToken(request);
    // 查询数据库根据用户id获取用户信息
    UcenterMember member = memberService.getById(memberId);
    return Result.ok().data("userInfo", member);
  }

  // 根据用户id获取用户信息
  @PostMapping("/getUserInfoOrder/{id}")
  public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {
    UcenterMember member = memberService.getById(id);
    // 把member对象里面值复制给UcenterMemberOrder对象
    UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
    BeanUtils.copyProperties(member, ucenterMemberOrder);
    return ucenterMemberOrder;
  }
}
