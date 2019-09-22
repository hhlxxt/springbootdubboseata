package com.zoro.service;

import com.baomidou.mybatisplus.service.IService;
import com.zoro.dto.CommodityDTO;
import com.zoro.entity.TStorage;
import com.zoro.response.ObjectResponse;

/**
 * 仓库服务
 *
 * @author heshouyou
 * @since 2019-01-13
 */
public interface ITStorageService extends IService<TStorage> {

    /**
     * 扣减库存
     */
    ObjectResponse decreaseStorage(CommodityDTO commodityDTO);
}
