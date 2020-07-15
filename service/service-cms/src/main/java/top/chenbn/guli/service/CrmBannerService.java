package top.chenbn.guli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.chenbn.guli.entity.CrmBanner;

import java.util.List;

/**
 * 首页banner表 服务类
 *
 * @author chenbn
 * @since 2020-07-14
 */
public interface CrmBannerService extends IService<CrmBanner> {

  // 查询所有banner
  List<CrmBanner> selectAllBanner();
}
