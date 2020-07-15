package top.chenbn.guli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.chenbn.guli.entity.CrmBanner;
import top.chenbn.guli.mapper.CrmBannerMapper;
import top.chenbn.guli.service.CrmBannerService;

import java.util.List;

/**
 * 首页banner表 服务实现类
 *
 * @author chenbn
 * @since 2020-07-14
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner>
    implements CrmBannerService {
  // 查询所有banner
  @Cacheable(value = "banner", key = "'selectIndexList'")
  @Override
  public List<CrmBanner> selectAllBanner() {
    // 根据id进行降序排列，显示排列之后前两条记录
    QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
    wrapper.orderByDesc("id");
    // last方法，拼接sql语句
    wrapper.last("limit 2");
    List<CrmBanner> list = baseMapper.selectList(wrapper);
    return list;
  }
}
