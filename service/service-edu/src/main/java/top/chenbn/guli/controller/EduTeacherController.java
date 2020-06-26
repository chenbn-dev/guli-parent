package top.chenbn.guli.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chenbn.guli.commonutil.Result;
import top.chenbn.guli.entity.EduTeacher;
import top.chenbn.guli.service.EduTeacherService;

import java.util.List;

/**
 * 讲师 前端控制器
 *
 * @author chenbn
 * @since 2020-06-26
 */
@RestController
@Api(description = "讲师管理模块")
@RequestMapping("/guli/edu/teacher")
public class EduTeacherController {
  @Autowired private EduTeacherService teacherService;
  // 1.查询讲师表中的所有数据
  @ApiOperation(value = "获取所有讲师列表")
  @GetMapping("/findAll")
  public Result findAllTeacher() {
    // 调用service的方法实现查询所有的操作
    List<EduTeacher> list = teacherService.list(null);
    return Result.ok().data("items", list);
  }

  // 2 逻辑删除讲师的方法
  @ApiOperation(value = "删除指定id的讲师")
  @DeleteMapping("/{id}") // {id} 表示id需要通过路径进行传递 localhost:8001/edu/1
  public Result removeTeacher(
      @ApiParam(name = "id", value = "讲师ID", required = true)
          // @PathVariable 获取路径中输入的id值
          @PathVariable
          String id) {
    boolean flag = teacherService.removeById(id);
    if (flag) {
      return Result.ok();
    } else {
      return Result.error();
    }
  }
}
