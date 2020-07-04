package top.chenbn.guli.listerer;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.chenbn.guli.entity.EduSubject;
import top.chenbn.guli.entity.excel.SubjectData;
import top.chenbn.guli.exceptionhandler.GuliException;
import top.chenbn.guli.service.EduSubjectService;

/**
 * @author chbn
 * @create 2020-07-02 12:49
 */
public class SubjectExcelListener extends AnalysisEventListener {
  // 因为SubjectExcelListener不能交给Spring去管理，需要自己new，不能注入其他对象
  // 不能实现数据库操作

  private EduSubjectService eduSubjectService;

  public SubjectExcelListener() {}

  public SubjectExcelListener(EduSubjectService eduSubjectService) {
    this.eduSubjectService = eduSubjectService;
  }

  @Override
  public void invoke(Object o, AnalysisContext analysisContext) {
    SubjectData subjectData = (SubjectData) o;
    if (subjectData == null) {
      throw new GuliException(20001, "文件数据为空");
    }
    // 一行一行读取，每次读取有两个值，第一个值一级分类，第二个值，二级分类
    EduSubject existOneSubject =
        this.existOneSubject(subjectData.getOneSubjectName(), eduSubjectService);
    // 判断一级分类不能重复添加
    // 表示表里面没有相同的一级分类
    if (existOneSubject == null) {
      existOneSubject = new EduSubject();
      existOneSubject.setParentId("0");
      existOneSubject.setTitle(subjectData.getOneSubjectName());
      eduSubjectService.save(existOneSubject);
    }
    // 获取一级分类的id值
    String pid = existOneSubject.getId();
    EduSubject existTwoSubject =
        this.existTwoSubject(subjectData.getTwoSubjectName(), eduSubjectService, pid);
    if (existTwoSubject == null) {
      existTwoSubject = new EduSubject();
      existTwoSubject.setParentId(pid);
      existTwoSubject.setTitle(subjectData.getTwoSubjectName());
      eduSubjectService.save(existTwoSubject);
    }
  }

  private EduSubject existOneSubject(String name, EduSubjectService eduSubjectService) {
    QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
    wrapper.eq("title", name);
    wrapper.eq("parent_id", "0");
    EduSubject oneSubject = eduSubjectService.getOne(wrapper);
    return oneSubject;
  }

  // 判断一级分类不能重复添加

  private EduSubject existTwoSubject(String name, EduSubjectService eduSubjectService, String pid) {
    QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
    wrapper.eq("title", name);
    wrapper.eq("parent_id", pid);
    EduSubject twoSubject = eduSubjectService.getOne(wrapper);
    return twoSubject;
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {}
}
