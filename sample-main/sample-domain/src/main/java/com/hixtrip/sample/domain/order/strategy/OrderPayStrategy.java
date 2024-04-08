package com.hixtrip.sample.domain.order.strategy;

import com.hixtrip.sample.domain.pay.model.CommandPay;

/**
 * 订单支付策略
 */
public interface OrderPayStrategy {

    /**
     * 订单支付处理
     */
    void handler(CommandPay commandPay);
}
