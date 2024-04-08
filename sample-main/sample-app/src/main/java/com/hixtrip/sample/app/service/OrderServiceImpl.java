package com.hixtrip.sample.app.service;

import com.hixtrip.sample.app.api.OrderService;
import com.hixtrip.sample.app.convertor.OrderConvertor;
import com.hixtrip.sample.client.order.dto.CommandOderCreateDTO;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.client.order.vo.CommandOrderPayVO;
import com.hixtrip.sample.client.order.vo.CommandOrderVO;
import com.hixtrip.sample.domain.order.OrderDomainService;
import com.hixtrip.sample.domain.order.factory.OrderPayHandlerStrategyFactory;
import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.order.strategy.OrderPayStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * app层负责处理request请求，调用领域服务
 */
@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDomainService orderDomainService;

    @Override
    public CommandOrderVO createOrder(CommandOderCreateDTO commandOderCreateDTO) {

        // 数据合理性校验...

        // 转换创建订单所需数据类型
        Order order = Order.builder()
                .skuId(commandOderCreateDTO.getSkuId())
                .userId(commandOderCreateDTO.getUserId())
                .amount(commandOderCreateDTO.getAmount()).build();

        boolean result = orderDomainService.createOrder(order);
        return CommandOrderVO.resultProcessing(result);
    }

    @Override
    public CommandOrderPayVO payCallback(CommandPayDTO commandPayDTO) {

        // 数据合理性校验...
        // TODO 如果重复支付是一种策略的分支，那么直接增加对应策略。如果是兜底操作，那么在获取获取策略前判断是否已经支付及操作...

        // 通过工厂获取对应的处理方法
        OrderPayStrategy strategy = OrderPayHandlerStrategyFactory.getStrategy(commandPayDTO.getPayStatus());
        // 要是策略没有结果，直接拿支付状态定义结果。要是策略有结果就直接转化
        if (null == strategy) {
            // 没有对应策略的处理
            return null;
        }
        // 对应策略的处理
        strategy.handler(OrderConvertor.INSTANCE.commandPayDTOToCommandPay(commandPayDTO));
        return null;
    }
}
