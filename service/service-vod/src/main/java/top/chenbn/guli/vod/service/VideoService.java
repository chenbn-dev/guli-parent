package top.chenbn.guli.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author chbn
 * @create 2020-07-11 10:30
 */
public interface VideoService {
  /**
   * 上传视频到阿里云
   *
   * @param file
   * @return 视频id
   */
  String uploadAliyunVideo(MultipartFile file);

  /**
   * 删除多个阿里云视频的方法
   *
   * @param videoIdList
   */
  void removeMoreAlyVideo(List videoIdList);
}
