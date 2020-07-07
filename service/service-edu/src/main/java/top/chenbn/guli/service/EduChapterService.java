package top.chenbn.guli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.chenbn.guli.entity.EduChapter;
import top.chenbn.guli.entity.chapter.ChapterVO;

import java.util.List;

/**
 * 课程 服务类
 *
 * @author chenbn
 * @since 2020-07-04
 */
public interface EduChapterService extends IService<EduChapter> {

  /**
   * 根据课程id获取该课程的章节和小结
   *
   * @param courseId
   */
  List<ChapterVO> getChapterVideoByCourseId(String courseId);

  /**
   * 删除章节的方法
   *
   * @param chapterId
   */
  boolean deleteChapter(String chapterId);
}
