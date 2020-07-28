package top.chenbn.guli.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import top.chenbn.guli.common.util.ordervo.UcenterMemberOrder;

@Component
@FeignClient("service-ucenter-8150")
public interface UcenterClient {

  // 根据用户id获取用户信息
  @PostMapping("/edu/ucenter/member/getUserInfoOrder/{id}")
  UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);
}
