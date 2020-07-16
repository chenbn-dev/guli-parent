package top.chenbn.guli.controller;

import org.springframework.web.bind.annotation.*;
import top.chenbn.guli.common.util.Result;

/**
 * @author chbn
 * @create 2020-06-28 12:54
 */
@RestController
@RequestMapping("/edu/service/user")
@CrossOrigin // 解决跨域
public class EduLoginController {
  // login
  @PostMapping("/login")
  public Result login() {
    return Result.ok().data("token", "admin");
  }

  // info
  @GetMapping("/info")
  public Result info() {
    return Result.ok()
        .data("roles", "[admin]")
        .data("name", "admin")
        .data(
            "avatar",
            "http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E5%A4%B4%E5%83%8F%E5%9B%BE%E7%89%87&hs=0&pn=0&spn=0&di=67980&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=2752748333%2C3965197483&os=2367618880%2C3130240342&simid=3333386394%2C237797138&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=head&bdtype=0&oriquery=%E5%A4%B4%E5%83%8F%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fa.hiphotos.baidu.com%2Fzhidao%2Fwh%3D450%2C600%2Fsign%3Dbbba1da0d60735fa91a546bdab612385%2F9825bc315c6034a84e7d073ac9134954082376e9.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3F1q_z%26e3Bptjkw_z%26e3Bv54AzdH3FrAzdH3Fc9n88ddbmb&gsm=1&islist=&querylist=");
  }
}
