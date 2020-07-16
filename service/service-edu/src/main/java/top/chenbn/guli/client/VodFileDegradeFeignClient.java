package top.chenbn.guli.client;

import org.springframework.stereotype.Component;
import top.chenbn.guli.common.util.Result;

import java.util.List;

/**
 * @author chbn
 * @create 2020-07-12 23:10
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
  @Override
  public Result removeAliyunVideo(String id) {
    return Result.error().message("删除视频出错");
  }

  @Override
  public Result deleteBatch(List<String> videoIdList) {
    return Result.error().message("删除多个视频出错");
  }
}
