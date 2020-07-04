package top.chenbn.guli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chenbn.guli.service.EduVideoService;

/**
 * 课程视频 前端控制器
 *
 * @author chenbn
 * @since 2020-07-04
 */
@CrossOrigin
@RestController
@RequestMapping("/guli/edu/video")
public class EduVideoController {

  @Autowired private EduVideoService videoService;
}
