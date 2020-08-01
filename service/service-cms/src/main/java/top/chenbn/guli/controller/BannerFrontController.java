package top.chenbn.guli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chenbn.guli.common.util.Result;
import top.chenbn.guli.entity.CrmBanner;
import top.chenbn.guli.service.CrmBannerService;

import java.util.List;

/**
 * 前台bannber显示
 *
 * @author chenbn
 * @since 2020-07-14
 */
@RestController
@RequestMapping("/edu/cms/banner-front")
// @CrossOrigin
public class BannerFrontController {
  @Autowired private CrmBannerService bannerService;

  // 查询所有banner
  @GetMapping("/getAllBanner")
  public Result getAllBanner() {
    List<CrmBanner> list = bannerService.selectAllBanner();
    return Result.ok().data("list", list);
  }
}
