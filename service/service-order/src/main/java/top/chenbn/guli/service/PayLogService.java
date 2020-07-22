package top.chenbn.guli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.chenbn.guli.entity.PayLog;

import java.util.Map;

/**
 * 支付日志表 服务类
 *
 * @author chenbn
 * @since 2020-07-20
 */
public interface PayLogService extends IService<PayLog> {
  // 生成微信支付二维码接口
  Map createNatvie(String orderNo);

  // 根据订单号查询订单支付状态
  Map<String, String> queryPayStatus(String orderNo);

  // 向支付表添加记录，更新订单状态
  void updateOrdersStatus(Map<String, String> map);
}
