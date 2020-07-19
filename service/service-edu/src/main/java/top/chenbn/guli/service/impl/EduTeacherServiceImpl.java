package top.chenbn.guli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.chenbn.guli.entity.EduTeacher;
import top.chenbn.guli.mapper.EduTeacherMapper;
import top.chenbn.guli.service.EduTeacherService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 讲师 服务实现类
 *
 * @author chenbn
 * @since 2020-06-26
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher>
    implements EduTeacherService {

  @Override
  public Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageParam) {
    QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
    wrapper.orderByDesc("id");
    // 把分页数据封装到pageTeacher对象
    baseMapper.selectPage(pageParam, wrapper);

    List<EduTeacher> records = pageParam.getRecords();
    long current = pageParam.getCurrent();
    long pages = pageParam.getPages();
    long size = pageParam.getSize();
    long total = pageParam.getTotal();
    boolean hasNext = pageParam.hasNext(); // 是否有下一页
    boolean hasPrevious = pageParam.hasPrevious(); // 是否有上一页

    // 把分页数据获取出来，放到map集合
    Map<String, Object> map = new HashMap<>();
    map.put("items", records);
    map.put("current", current);
    map.put("pages", pages);
    map.put("size", size);
    map.put("total", total);
    map.put("hasNext", hasNext);
    map.put("hasPrevious", hasPrevious);
    // map返回
    return map;
  }
}
