package com.hixtrip.sample.domain.order.factory;

import com.hixtrip.sample.domain.order.strategy.OrderPayStrategy;
import com.hixtrip.sample.domain.sample.repository.impl.OrderPayFailStrategy;
import com.hixtrip.sample.domain.sample.repository.impl.OrderPaySuccessStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单支付处理策略工厂
 */
public class OrderPayHandlerStrategyFactory {

    private static final Map<String, OrderPayStrategy> MAP = new HashMap<>();

    static {
        // 支付状态采用枚举或者在对应实现策略定义唯一标识来引用
        MAP.put("1", new OrderPaySuccessStrategy());
        MAP.put("2", new OrderPayFailStrategy());
    }

    public static OrderPayStrategy getStrategy(String type) {
        return MAP.get(type);
    }
}
