package top.chenbn.guli.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chbn
 * @create 2020-06-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuliException extends RuntimeException {

  @ApiModelProperty(value = "状态码")
  private Integer code;

  private String msg;
}
