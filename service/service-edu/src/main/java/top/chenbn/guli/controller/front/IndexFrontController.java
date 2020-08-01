package top.chenbn.guli.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chenbn.guli.common.util.Result;
import top.chenbn.guli.entity.EduCourse;
import top.chenbn.guli.entity.EduTeacher;
import top.chenbn.guli.service.EduCourseService;
import top.chenbn.guli.service.EduTeacherService;

import java.util.List;

@RestController
@RequestMapping("/edu/service/banner-front")
//@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    //查询前8条热门课程，查询前4条名师
    @GetMapping("/index")
    public Result index() {
        //查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> eduList = courseService.list(wrapper);

        //查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);

        return Result.ok().data("eduList",eduList).data("teacherList",teacherList);
    }

}
