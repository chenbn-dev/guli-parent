package top.chenbn.guli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chenbn.guli.entity.EduChapter;
import top.chenbn.guli.entity.EduVideo;
import top.chenbn.guli.entity.chapter.ChapterVO;
import top.chenbn.guli.entity.chapter.VideoVO;
import top.chenbn.guli.exceptionhandler.GuliException;
import top.chenbn.guli.mapper.EduChapterMapper;
import top.chenbn.guli.service.EduChapterService;
import top.chenbn.guli.service.EduVideoService;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程 服务实现类
 *
 * @author chenbn
 * @since 2020-07-04
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter>
    implements EduChapterService {

  @Autowired private EduVideoService videoService;

  @Override
  public List<ChapterVO> getChapterVideoByCourseId(String courseId) {
    // 1.根据课程id查询课程所有的章节
    QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
    wrapperChapter.eq("course_id", courseId);
    List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);
    // 2.根据课程id查询课程所有的小节
    QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
    wrapperVideo.eq("course_id", courseId);
    List<EduVideo> eduVideoList = videoService.list(wrapperVideo);
    // 创建list集合，用于最终封装数据
    List<ChapterVO> finalList = new ArrayList<>();
    // 3.遍历章节list集合进行封装
    for (EduChapter eduChapter : eduChapterList) {
      ChapterVO chapterVO = new ChapterVO();
      BeanUtils.copyProperties(eduChapter, chapterVO);
      finalList.add(chapterVO);
      // 4.遍历小结list集合进行封装
      List<VideoVO> videoVOS = new ArrayList<>();
      for (EduVideo eduVideo : eduVideoList) {
        if (eduVideo.getChapterId().equals(eduChapter.getId())) {
          VideoVO videoVO = new VideoVO();
          BeanUtils.copyProperties(eduVideo, videoVO);
          videoVOS.add(videoVO);
        }
      }
      chapterVO.setChildren(videoVOS);
    }

    return finalList;
  }

  @Override
  public boolean deleteChapter(String chapterId) {
    QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
    wrapper.eq("chapter_id", chapterId);
    int count = videoService.count(wrapper);
    if (count > 0) {
      throw new GuliException(20001, "不能删除");
    } else {
      int result = baseMapper.deleteById(chapterId);
      return result > 0;
    }
  }
}
