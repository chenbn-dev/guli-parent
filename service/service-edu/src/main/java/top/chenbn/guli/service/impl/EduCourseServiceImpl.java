package top.chenbn.guli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.chenbn.guli.entity.EduCourse;
import top.chenbn.guli.entity.EduCourseDescription;
import top.chenbn.guli.entity.frontvo.CourseFrontVo;
import top.chenbn.guli.entity.frontvo.CourseWebVo;
import top.chenbn.guli.entity.vo.CourseInfoVO;
import top.chenbn.guli.entity.vo.CoursePublishVO;
import top.chenbn.guli.exceptionhandler.GuliException;
import top.chenbn.guli.mapper.EduCourseMapper;
import top.chenbn.guli.service.EduChapterService;
import top.chenbn.guli.service.EduCourseDescriptionService;
import top.chenbn.guli.service.EduCourseService;
import top.chenbn.guli.service.EduVideoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程 服务实现类
 *
 * @author chenbn
 * @since 2020-07-04
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse>
    implements EduCourseService {

  // 课程描述注入
  @Autowired private EduCourseDescriptionService courseDescriptionService;

  // 注入小节和章节service
  @Autowired private EduVideoService eduVideoService;

  @Autowired private EduChapterService chapterService;

  @Override
  public String saveCourseInfo(CourseInfoVO courseInfoVO) {
    // 1.向课程表添加课程基本信息
    EduCourse eduCourse = new EduCourse();
    BeanUtils.copyProperties(courseInfoVO, eduCourse);
    int rows = baseMapper.insert(eduCourse);
    if (rows == 0) {
      throw new GuliException(20001, "添加课程信息失败");
    }
    String cid = eduCourse.getId();
    // 2.向课程简介表添加课程简介
    EduCourseDescription courseDescription = new EduCourseDescription();
    courseDescription.setDescription(courseInfoVO.getDescription());
    // 设置描述id就是课程id
    courseDescription.setId(cid);
    courseDescriptionService.save(courseDescription);
    return cid;
  }

  @Override
  public CourseInfoVO getCourseInfo(String courseId) {
    // 1.查询课程表
    EduCourse eduCourse = baseMapper.selectById(courseId);
    CourseInfoVO courseInfoVO = new CourseInfoVO();
    BeanUtils.copyProperties(eduCourse, courseInfoVO);
    // 2.查询描述表
    EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
    courseInfoVO.setDescription(courseDescription.getDescription());
    return courseInfoVO;
  }

  @Override
  public void updateCourseInfo(CourseInfoVO courseInfoVO) {
    EduCourse eduCourse = new EduCourse();
    BeanUtils.copyProperties(courseInfoVO, eduCourse);
    int rows = baseMapper.updateById(eduCourse);
    if (rows == 0) {
      throw new GuliException(20001, "修改课程信息失败");
    }
    EduCourseDescription courseDescription = new EduCourseDescription();
    courseDescription.setId(courseInfoVO.getId());
    courseDescription.setDescription(courseInfoVO.getDescription());
    courseDescriptionService.updateById(courseDescription);
  }

  @Override
  public CoursePublishVO publishCourseInfo(String id) {
    CoursePublishVO coursePublishVO = baseMapper.getPublishCourseInfo(id);
    return coursePublishVO;
  }

  @Override
  public void removeCourse(String courseId) { // 1 根据课程id删除小节
    eduVideoService.removeVideoByCourseId(courseId);
    // 2 根据课程id删除章节
    chapterService.removeChapterByCourseId(courseId);
    // 3 根据课程id删除描述
    courseDescriptionService.removeById(courseId);
    // 4 根据课程id删除课程本身
    int result = baseMapper.deleteById(courseId);
    // 失败返回
    if (result == 0) {
      throw new GuliException(20001, "删除失败");
    }
  }

  /**
   * 1 条件查询带分页查询课程
   *
   * @param pageParam
   * @param courseFrontVo
   * @return
   */
  @Override
  public Map<String, Object> getCourseFrontList(
      Page<EduCourse> pageParam, CourseFrontVo courseFrontVo) {
    // 2 根据讲师id查询所讲课程
    QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
    // 判断条件值是否为空，不为空拼接
    // 一级分类
    if (!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) {
      wrapper.eq("subject_parent_id", courseFrontVo.getSubjectParentId());
    }
    // 二级分类
    if (!StringUtils.isEmpty(courseFrontVo.getSubjectId())) {
      wrapper.eq("subject_id", courseFrontVo.getSubjectId());
    }
    // 关注度
    if (!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) {
      wrapper.orderByDesc("buy_count");
    }
    // 最新
    if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) {
      wrapper.orderByDesc("gmt_create");
    }
    // 价格
    if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {
      wrapper.orderByDesc("price");
    }

    baseMapper.selectPage(pageParam, wrapper);

    List<EduCourse> records = pageParam.getRecords();
    long current = pageParam.getCurrent();
    long pages = pageParam.getPages();
    long size = pageParam.getSize();
    long total = pageParam.getTotal();
    boolean hasNext = pageParam.hasNext(); // 下一页
    boolean hasPrevious = pageParam.hasPrevious(); // 上一页

    // 把分页数据获取出来，放到map集合
    Map<String, Object> map = new HashMap<>();
    map.put("items", records);
    map.put("current", current);
    map.put("pages", pages);
    map.put("size", size);
    map.put("total", total);
    map.put("hasNext", hasNext);
    map.put("hasPrevious", hasPrevious);

    // map返回
    return map;
  }

  // 根据课程id，编写sql语句查询课程信息
  @Override
  public CourseWebVo getBaseCourseInfo(String courseId) {
    return baseMapper.getBaseCourseInfo(courseId);
  }
}
