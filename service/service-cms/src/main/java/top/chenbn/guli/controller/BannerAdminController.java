package top.chenbn.guli.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chenbn.guli.common.util.Result;
import top.chenbn.guli.entity.CrmBanner;
import top.chenbn.guli.service.CrmBannerService;

/**
 * 后台banner管理接口
 *
 * @author chenbn
 * @since 2020-07-14
 */
@RestController
@RequestMapping("/edu/cms/banner-admin")
// @CrossOrigin
public class BannerAdminController {
  @Autowired private CrmBannerService bannerService;

  /**
   * 1 分页查询banner
   *
   * @param page
   * @param limit
   * @return
   */
  @GetMapping("/pageBanner/{page}/{limit}")
  public Result pageBanner(@PathVariable long page, @PathVariable long limit) {
    Page<CrmBanner> pageBanner = new Page<>(page, limit);
    bannerService.page(pageBanner, null);
    return Result.ok().data("items", pageBanner.getRecords()).data("total", pageBanner.getTotal());
  }

  /**
   * 2 添加banner
   *
   * @param crmBanner
   * @return
   */
  @PostMapping("/addBanner")
  public Result addBanner(@RequestBody CrmBanner crmBanner) {
    bannerService.save(crmBanner);
    return Result.ok();
  }

  @ApiOperation(value = "获取Banner")
  @GetMapping("/get/{id}")
  public Result get(@PathVariable String id) {
    CrmBanner banner = bannerService.getById(id);
    return Result.ok().data("item", banner);
  }

  @ApiOperation(value = "修改Banner")
  @PutMapping("/update")
  public Result updateById(@RequestBody CrmBanner banner) {
    bannerService.updateById(banner);
    return Result.ok();
  }

  @ApiOperation(value = "删除Banner")
  @DeleteMapping("/remove/{id}")
  public Result remove(@PathVariable String id) {
    bannerService.removeById(id);
    return Result.ok();
  }
}
