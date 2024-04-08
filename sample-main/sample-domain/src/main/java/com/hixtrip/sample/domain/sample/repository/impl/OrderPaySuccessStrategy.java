package com.hixtrip.sample.domain.sample.repository.impl;

import com.hixtrip.sample.domain.order.OrderDomainService;
import com.hixtrip.sample.domain.order.strategy.OrderPayStrategy;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 支付成功处理
 */
@Component
public class OrderPaySuccessStrategy implements OrderPayStrategy {

    @Autowired
    private OrderDomainService orderDomainService;

    @Override
    public void handler(CommandPay commandPay) {
        orderDomainService.orderPaySuccess(commandPay);
    }
}
