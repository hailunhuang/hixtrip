package com.hixtrip.sample.client.order.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandOrderVO {

    /**
     * 状态码(全局)
     */
    private String code;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 创建订单结果的展示对象
     *
     * @param result 结果
     * @return {@link CommandOrderVO}
     */
    public static CommandOrderVO resultProcessing(boolean result) {
        return result ? success() : fail();
    }

    /**
     * 创建订单成功的返回对象
     *
     * @return {@link CommandOrderVO}
     */
    public static CommandOrderVO success() {
        return new CommandOrderVO();
    }

    /**
     * 创建订单失败的返回对象
     *
     * @return {@link CommandOrderVO}
     */
    public static CommandOrderVO fail() {
        return new CommandOrderVO();
    }
}
