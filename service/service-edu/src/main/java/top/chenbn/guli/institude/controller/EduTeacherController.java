package top.chenbn.guli.institude.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chenbn.guli.institude.entity.EduTeacher;
import top.chenbn.guli.institude.service.EduTeacherService;

import java.util.List;

/**
 * 讲师 前端控制器
 *
 * @author chenbn
 * @since 2020-06-26
 */
@RestController
@RequestMapping("/guli/institude/edu/teacher")
public class EduTeacherController {
  @Autowired private EduTeacherService eduTeacherService;
  // 1.查询讲师表中的所有数据
  @GetMapping("/findAll")
  public List<EduTeacher> findAllTeacher() {
    // 调用service的方法实现查询所有的操作
    List<EduTeacher> eduTeacherList = eduTeacherService.list(null);
    return eduTeacherList;
  }
}
