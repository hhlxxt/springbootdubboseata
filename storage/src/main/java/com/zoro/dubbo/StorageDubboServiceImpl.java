package com.zoro.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoro.dto.CommodityDTO;
import com.zoro.response.ObjectResponse;
import com.zoro.service.ITStorageService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description
 */
@Service(version = "1.0.0",protocol = "${dubbo.protocol.id}",
        application = "${dubbo.application.id}",registry = "${dubbo.registry.id}",
        timeout = 3000)
@Slf4j
public class StorageDubboServiceImpl implements StorageDubboService {

    @Autowired
    private ITStorageService storageService;

    public ObjectResponse decreaseStorage(CommodityDTO commodityDTO) {
        log.info("库存服务全局事务id ：{}" , RootContext.getXID());
        return storageService.decreaseStorage(commodityDTO);
    }
}
