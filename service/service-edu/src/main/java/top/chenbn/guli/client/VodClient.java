package top.chenbn.guli.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import top.chenbn.guli.common.util.Result;

import java.util.List;

/**
 * @author chbn
 * @create 2020-07-12 16:26
 */
@Component
@FeignClient(name = "service-vod-8003", fallback = VodFileDegradeFeignClient.class)
public interface VodClient {
  /**
   * 定义调用方法的路径 根据视频id删除阿里云视频
   *
   * @param id @PathVariable注解一定要指定参数名称，否则出错
   * @return
   */
  @DeleteMapping("/edu/vod/video/removeAliyunVideo/{id}")
  Result removeAliyunVideo(@PathVariable("id") String id);

  /**
   * 定义调用删除多个视频的方法
   *
   * @param videoIdList 多个视频id的list集合
   * @return
   */
  @DeleteMapping("/edu/vod/video/delete-batch")
  public Result deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
