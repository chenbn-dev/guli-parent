package top.chenbn.guli.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import top.chenbn.guli.common.util.ordervo.CourseWebVoOrder;

@Component
@FeignClient("service-edu-8001")
public interface EduClient {

  // 根据课程id查询课程信息
  @PostMapping("/edu/service/coursefront/getCourseInfoOrder/{id}")
  public CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String id);
}
