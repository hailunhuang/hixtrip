package com.hixtrip.sample.client.order.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandOrderPayVO {

    /**
     * 状态码(全局)
     */
    private String code;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 创建订单支付结果的展示对象
     *
     * @param status 状态
     * @return {@link CommandOrderVO}
     */
    public static CommandOrderPayVO resultProcessing(String status) {
        // 状态可以用枚举来定义
        return switch (status) {
            case "1" -> new CommandOrderPayVO("111", "");
            case "2" -> new CommandOrderPayVO("222", "");
            default -> new CommandOrderPayVO("", "");
        };
    }

}
