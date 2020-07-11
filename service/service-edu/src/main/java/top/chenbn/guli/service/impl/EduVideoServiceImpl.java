package top.chenbn.guli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.chenbn.guli.entity.EduVideo;
import top.chenbn.guli.mapper.EduVideoMapper;
import top.chenbn.guli.service.EduVideoService;

/**
 * 课程视频 服务实现类
 *
 * @author chenbn
 * @since 2020-07-04
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo>
    implements EduVideoService {

  // TODO 删除小节，删除对应视频文件

  /**
   * 根据课程id删除小节
   *
   * @param courseId
   */
  @Override
  public void removeVideoByCourseId(String courseId) {
    QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
    wrapper.eq("course_id", courseId);
    baseMapper.delete(wrapper);
  }
}
