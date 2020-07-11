package top.chenbn.guli.service;

import top.chenbn.guli.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author chenbn
 * @since 2020-07-04
 */
public interface EduVideoService extends IService<EduVideo> {

        void removeVideoByCourseId(String courseId);
}
