package com.zoro.dubbo;

import com.zoro.dto.CommodityDTO;
import com.zoro.response.ObjectResponse;

/**
 * @Description  仓库服务
 */
public interface StorageDubboService {

    /**
     * 扣减库存
     */
    ObjectResponse decreaseStorage(CommodityDTO commodityDTO);
}
