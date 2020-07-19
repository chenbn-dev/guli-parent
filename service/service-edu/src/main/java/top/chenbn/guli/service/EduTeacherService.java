package top.chenbn.guli.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.chenbn.guli.entity.EduTeacher;

import java.util.Map;

/**
 * 讲师 服务类
 *
 * @author chenbn
 * @since 2020-06-26
 */
public interface EduTeacherService extends IService<EduTeacher> {
  /**
   * 1 分页查询讲师的方法
   *
   * @param pageTeacher
   * @return
   */
  Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
