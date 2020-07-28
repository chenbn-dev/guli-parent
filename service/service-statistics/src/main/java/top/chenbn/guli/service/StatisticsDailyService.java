package top.chenbn.guli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.chenbn.guli.entity.StatisticsDaily;

import java.util.Map;

/**
 * 网站统计日数据 服务类
 *
 * @author chenbn
 * @since 2020-07-28
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

  // 统计某一天注册人数,生成统计数据
  void registerCount(String day);

  // 图表显示，返回两部分数据，日期json数组，数量json数组
  Map<String, Object> getShowData(String type, String begin, String end);
}
