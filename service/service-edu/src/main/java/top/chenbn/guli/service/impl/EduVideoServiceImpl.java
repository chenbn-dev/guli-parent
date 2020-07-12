package top.chenbn.guli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.chenbn.guli.client.VodClient;
import top.chenbn.guli.entity.EduVideo;
import top.chenbn.guli.mapper.EduVideoMapper;
import top.chenbn.guli.service.EduVideoService;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程视频 服务实现类
 *
 * @author chenbn
 * @since 2020-07-04
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo>
    implements EduVideoService {
  @Autowired private VodClient vodClient;
  // TODO 删除小节，删除对应视频文件

  /**
   * 根据课程id删除小节
   *
   * @param courseId
   */
  @Override
  public void removeVideoByCourseId(String courseId) {
    // 1.根据课程id查询所有的视频id
    QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
    wrapperVideo.eq("course_id", courseId);
    wrapperVideo.select("video_source_id");
    List<EduVideo> eduVideoList = baseMapper.selectList(wrapperVideo);
    List<String> videoIds = new ArrayList<>();
    for (EduVideo eduVideo : eduVideoList) {
      String videoSourceId = eduVideo.getVideoSourceId();
      if (!StringUtils.isEmpty(videoSourceId)) {
        // 放到videoIds集合里面
        videoIds.add(videoSourceId);
      }
    }
    if (videoIds.size() > 0) {
      vodClient.deleteBatch(videoIds);
    }
    QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
    wrapper.eq("course_id", courseId);
    baseMapper.delete(wrapper);
  }
}
