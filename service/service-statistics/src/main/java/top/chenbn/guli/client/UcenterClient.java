package top.chenbn.guli.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.chenbn.guli.common.util.Result;

@Component
@FeignClient("service-ucenter-8150")
public interface UcenterClient {

  // 查询某一天注册人数
  @GetMapping("/edu/ucenter/member/countRegister/{day}")
  public Result countRegister(@PathVariable("day") String day);
}
