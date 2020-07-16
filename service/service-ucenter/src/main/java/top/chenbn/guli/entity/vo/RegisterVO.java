package top.chenbn.guli.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chbn
 * @create 2020-07-16 22:42
 */
@Data
public class RegisterVO {
  @ApiModelProperty(value = "昵称")
  private String nickname;

  @ApiModelProperty(value = "手机号")
  private String mobile;

  @ApiModelProperty(value = "密码")
  private String password;

  @ApiModelProperty(value = "验证码")
  private String code;
}
