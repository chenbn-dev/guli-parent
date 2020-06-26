package top.chenbn.guli.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.chenbn.guli.entity.EduTeacher;
import top.chenbn.guli.mapper.EduTeacherMapper;
import top.chenbn.guli.service.EduTeacherService;

/**
 * 讲师 服务实现类
 *
 * @author chenbn
 * @since 2020-06-26
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher>
    implements EduTeacherService {}
