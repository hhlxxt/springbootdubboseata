package com.zoro.dubbo;

import com.zoro.dto.OrderDTO;
import com.zoro.response.ObjectResponse;

/**
 * @Description  订单服务接口
 */
public interface OrderDubboService {

    /**
     * 创建订单
     */
    ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO);
}
