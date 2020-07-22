package top.chenbn.guli.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chenbn.guli.common.util.JwtUtils;
import top.chenbn.guli.common.util.Result;
import top.chenbn.guli.entity.Order;
import top.chenbn.guli.service.OrderService;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单 前端控制器
 *
 * @author chenbn
 * @since 2020-07-20
 */
@CrossOrigin
@RestController
@RequestMapping("/order/service/order")
@Api(value = "订单模块 Api", tags = "订单模块")
public class OrderController {
  @Autowired private OrderService orderService;

  /**
   * 根据课程id和用户id创建订单，返回订单id
   *
   * @param courseId
   * @param request
   * @return
   */
  @ApiOperation("生成订单的方法")
  @PostMapping("/createOrder/{courseId}")
  public Result save(@PathVariable String courseId, HttpServletRequest request) {
    String orderId = orderService.createOrders(courseId, JwtUtils.getMemberIdByJwtToken(request));
    return Result.ok().data("orderId", orderId);
  }

  @ApiOperation("根据订单id查询订单信息")
  @GetMapping("/getOrderInfo/{orderId}")
  public Result getOrderInfo(@PathVariable String orderId) {
    QueryWrapper<Order> wrapper = new QueryWrapper<>();
    wrapper.eq("order_no", orderId);
    Order order = orderService.getOne(wrapper);
    return Result.ok().data("item", order);
  }
}
