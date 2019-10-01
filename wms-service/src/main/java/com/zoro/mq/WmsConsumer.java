package com.zoro.mq;

import com.rabbitmq.client.Channel;
import com.zoro.dto.OrderDTO;
import com.zoro.entity.WmsModel;
import com.zoro.service.WmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class WmsConsumer {

    @Autowired
    private  WmsService wmsService;


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.order.queue.name}",
                    durable="${spring.rabbitmq.listener.order.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.order.exchange.name}",
                    durable="${spring.rabbitmq.listener.order.exchange.durable}",
                    type= "${spring.rabbitmq.listener.order.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.order.key}"
    )
    )
    @RabbitHandler
    public void onOrderMessage(@Payload OrderDTO order,
                               Channel channel,
                               @Headers Map<String, Object> headers) throws Exception {
        log.info("物流服务接收订单信息为:{}",order);
        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        WmsModel wms = new WmsModel() ;
        wms.setAddress("test");
        wms.setOrderId(order.getOrderNo());
        wms.setCreateTime(new Date());
        wms.setUpdateTime(new Date());
        wms.setWmsStatus("1");
        log.info("物流信息{}",wms);
        wmsService.createWms(wms);
        //手工ACK
        channel.basicAck(deliveryTag, false);
    }

}
