package top.chenbn.guli.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.chenbn.guli.commonutil.Result;
import top.chenbn.guli.entity.EduTeacher;
import top.chenbn.guli.entity.vo.TeacherQueryVO;
import top.chenbn.guli.service.EduTeacherService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  // 7、讲师修改功能
  @PostMapping("/updateTeacher")
  public Result updateTeacher(@RequestBody EduTeacher eduTeacher) {
    boolean flag = teacherService.updateById(eduTeacher);
    if (flag) {
      return Result.ok();
    } else {
      return Result.error();
    }
  }

  // 6、根据讲师ID进行查询
  @GetMapping("/getTeacher/{id}")
  public Result getTeacher(@PathVariable Integer id) {
    EduTeacher eduTeacher = teacherService.getById(id);
    return Result.ok().data("teacher", eduTeacher);
  }

  // 5.添加讲师的接口方法
  @PostMapping("/addTeacher")
  public Result addTeacher(@RequestBody EduTeacher eduTeacher) {
    boolean save = teacherService.save(eduTeacher);
    if (save) {
      return Result.ok();
    } else {
      return Result.error();
    }
  }

  // 4.条件查询带分页的方法
  @PostMapping("/pageTeacherCondition/{currentPage}/{limit}")
  public Result pageTeacherCondition(
      @PathVariable Long currentPage,
      @PathVariable Long limit,
      // 使用json传递数据，把json数据封装到对应的对象里面
      @RequestBody(required = false) TeacherQueryVO teacherQueryVO) {
    // 1.创建page对象
    Page<EduTeacher> pageTeacher = new Page<>(currentPage, limit);
    // 2.构建条件
    QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
    // 多条件组合查询
    String name = teacherQueryVO.getName();
    Integer level = teacherQueryVO.getLevel();
    String begin = teacherQueryVO.getBegin();
    String end = teacherQueryVO.getEnd();
    // 判断条件值是否为空，如果不为空，拼接条件
    if (!StringUtils.isEmpty(name)) {
      wrapper.like("name", name);
    }
    if (!StringUtils.isEmpty(level)) {
      wrapper.eq("level", level);
    }
    if (!StringUtils.isEmpty(begin)) {
      wrapper.ge("gmt_create", begin);
    }
    if (!StringUtils.isEmpty(end)) {
      wrapper.le("gmt_modified", end);
    }
    // 调用方法实现条件分页查询
    teacherService.page(pageTeacher, wrapper);
    Long totalPage = pageTeacher.getTotal();
    List<EduTeacher> records = pageTeacher.getRecords();
    return Result.ok().data("totalPage", totalPage).data("rows", records);
  }

  /**
   * 3.分页查询方法
   *
   * @param currentPage 当前页码
   * @param limit 每页记录数
   * @return
   */
  @GetMapping("/pageTeacher/{currentPage}/{limit}")
  public Result pageListTeacher(@PathVariable Long currentPage, @PathVariable Long limit) {
    // 创建page对象
    Page<EduTeacher> pageTeacher = new Page<>(currentPage, limit);
    // 调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
    teacherService.page(pageTeacher, null);
    long totalPage = pageTeacher.getTotal();
    List<EduTeacher> records = pageTeacher.getRecords();
    // 返回结果第一种写法
    //    return Result.ok().data("totalPage",totalPage).data("rows",records);
    // 返回结果第二种写法
    Map map = new HashMap();
    map.put("totalPage", totalPage);
    map.put("rows", records);
    return Result.ok().data(map);
  }

  /**
   * 2. 根据id逻辑删除讲师的方法
   *
   * @param id
   * @return
   */
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

  // 1.查询讲师表中的所有数据
  @ApiOperation(value = "获取所有讲师列表")
  @GetMapping("/findAll")
  public Result findAllTeacher() {
    // 调用service的方法实现查询所有的操作
    List<EduTeacher> list = teacherService.list(null);
    return Result.ok().data("items", list);
  }
}
