package com.hixtrip.sample.domain.order.repository;

import com.hixtrip.sample.domain.order.model.Order;

/**
 *
 */
public interface OrderRepository {

    /**
     * 创建订单记录
     * TODO infra的db没有对应的订单实现，这边就只定义接口
     *
     * @param order 订单对象
     * @return {@link boolean}
     */
    boolean createOrder(Order order);
}
