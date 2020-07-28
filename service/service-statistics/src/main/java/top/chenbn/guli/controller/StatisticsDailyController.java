package top.chenbn.guli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chenbn.guli.common.util.Result;
import top.chenbn.guli.service.StatisticsDailyService;

import java.util.Map;

/**
 * 网站统计日数据 前端控制器
 *
 * @author chenbn
 * @since 2020-07-28
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/statistics/daily")
public class StatisticsDailyController {
  @Autowired private StatisticsDailyService staService;

  // 统计某一天注册人数,生成统计数据
  @PostMapping("registerCount/{day}")
  public Result registerCount(@PathVariable String day) {
    staService.registerCount(day);
    return Result.ok();
  }

  // 图表显示，返回两部分数据，日期json数组，数量json数组
  @GetMapping("showData/{type}/{begin}/{end}")
  public Result showData(
      @PathVariable String type, @PathVariable String begin, @PathVariable String end) {
    Map<String, Object> map = staService.getShowData(type, begin, end);
    return Result.ok().data(map);
  }
}
