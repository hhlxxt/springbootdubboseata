package com.zoro.service;

import com.zoro.entity.WmsMapper;
import com.zoro.entity.WmsModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class WmsService {

    @Autowired
    private WmsMapper wmsMapper ;

    @Transactional
    public int createWms(WmsModel wms){

        int result = wmsMapper.createWms(wms);
        if (result == 0){
            throw new RuntimeException("创建物流信息失败");
        }
        return result ;
    }

    public WmsModel selectWmsByOrderId(String orderId){

        return wmsMapper.selectWmsByOrderId(orderId) ;
    }
}
