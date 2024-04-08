package com.hixtrip.sample.app.api;

import com.hixtrip.sample.client.order.dto.CommandOderCreateDTO;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.client.order.vo.CommandOrderPayVO;
import com.hixtrip.sample.client.order.vo.CommandOrderVO;

/**
 * 订单的service层
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param commandOderCreateDTO 创建订单的请求 入参
     * @return {@link CommandOrderVO}
     */
    CommandOrderVO createOrder(CommandOderCreateDTO commandOderCreateDTO);

    /**
     * 支付结果回调处理
     *
     * @param commandPayDTO commandPayDTO
     * @return {@link CommandOrderPayVO}
     * @author HuangHaiLun 2024-04-08 14:19:04
     */
    CommandOrderPayVO payCallback(CommandPayDTO commandPayDTO);
}
