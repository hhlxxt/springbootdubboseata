package com.zoro.service;

import com.baomidou.mybatisplus.service.IService;
import com.zoro.entity.TOrder;
import com.zoro.dto.OrderDTO;
import com.zoro.response.ObjectResponse;

/**
 * <p>
 *  创建订单
 * </p>
 *
 * @author heshouyou
 * @since 2019-01-13
 */
public interface ITOrderService extends IService<TOrder> {

    /**
     * 创建订单
     */
    ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO);
}
