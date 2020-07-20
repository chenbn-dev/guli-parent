package top.chenbn.guli.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.chenbn.guli.entity.Order;
import top.chenbn.guli.mapper.OrderMapper;
import top.chenbn.guli.service.OrderService;

/**
 * 订单 服务实现类
 *
 * @author chenbn
 * @since 2020-07-20
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {}
