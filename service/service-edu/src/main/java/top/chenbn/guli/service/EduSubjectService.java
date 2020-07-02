package top.chenbn.guli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import top.chenbn.guli.entity.EduSubject;

/**
 * 课程科目 服务类
 *
 * @author chenbn
 * @since 2020-07-02
 */
public interface EduSubjectService extends IService<EduSubject> {
  /**
   * 添加课程分类
   *
   * @param file
   */
  void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);
}
