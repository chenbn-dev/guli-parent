package top.chenbn.guli.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author chbn
 * @create 2020-07-11 10:30
 */
public interface VodService {
  /**
   * 上传视频到阿里云
   *
   * @param file
   * @return 视频id
   */
  String uploadAliyunVideo(MultipartFile file);
}
