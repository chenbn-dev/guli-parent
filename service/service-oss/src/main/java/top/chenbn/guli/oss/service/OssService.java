package top.chenbn.guli.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author chbn
 * @create 2020-06-30 23:01
 */
public interface OssService {
  /**
   * 上传头像到oss
   *
   * @param file 文件对象
   * @return 存储文件的url地址
   */
  String uploadFileAvatar(MultipartFile file);
}
