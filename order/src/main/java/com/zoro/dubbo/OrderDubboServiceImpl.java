package com.zoro.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoro.service.ITOrderService;
import com.zoro.dto.OrderDTO;
import com.zoro.response.ObjectResponse;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description
 */
@Service(version = "1.0.0",protocol = "${dubbo.protocol.id}",
        application = "${dubbo.application.id}",registry = "${dubbo.registry.id}",
        timeout = 3000)
public class OrderDubboServiceImpl implements OrderDubboService {

    @Autowired
    private ITOrderService orderService;

    @Override
    public ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO) {
        System.out.println("全局事务id ：" + RootContext.getXID());
        return orderService.createOrder(orderDTO);
    }
}
