package top.chenbn.guli.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.chenbn.guli.entity.PayLog;
import top.chenbn.guli.mapper.PayLogMapper;
import top.chenbn.guli.service.PayLogService;

/**
 * 支付日志表 服务实现类
 *
 * @author chenbn
 * @since 2020-07-20
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {}
