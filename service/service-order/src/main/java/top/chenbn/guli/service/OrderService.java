package top.chenbn.guli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.chenbn.guli.entity.Order;

/**
 * 订单 服务类
 *
 * @author chenbn
 * @since 2020-07-20
 */
public interface OrderService extends IService<Order> {
  // 1 生成订单的方法
  String createOrders(String courseId, String memberIdByJwtToken);
}
