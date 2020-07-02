package top.chenbn.guli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.chenbn.guli.commonutil.Result;
import top.chenbn.guli.service.EduSubjectService;

/**
 * 课程科目 前端控制器
 *
 * @author chenbn
 * @since 2020-07-02
 */
@RestController
@RequestMapping("/edu/service/subject")
@CrossOrigin
public class EduSubjectController {
  @Autowired private EduSubjectService eduSubjectService;
  /**
   * 添加课程分类，获取上传过来的文件，把文件内容读取出来
   *
   * @param file 上传过来的excel文件
   * @return
   */
  @PostMapping("/add/subject")
  public Result addSubject(MultipartFile file) {
    eduSubjectService.saveSubject(file, eduSubjectService);
    return Result.ok();
  }
}
