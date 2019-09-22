package com.zoro.controller;

import com.zoro.dto.BusinessDTO;
import com.zoro.response.ObjectResponse;
import com.zoro.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description  Dubbo业务执行入口
 */
@RestController
@RequestMapping("/business/dubbo")
@Slf4j
public class BusinessController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessController.class);


    @Autowired
    private BusinessService businessService;

    /**
     * 模拟用户购买商品下单业务逻辑流程
     * @Param:
     * @Return:
     */
    @PostMapping("/buy")
    ObjectResponse handleBusiness(@RequestBody BusinessDTO businessDTO){
        LOGGER.info("请求参数：{}",businessDTO.toString());
        return businessService.handleBusiness(businessDTO);
    }

    /**
     * 模拟用户购买商品下单业务逻辑流程  回滚
     * @Param:
     * @Return:
     */
    @PostMapping("/roll")
    ObjectResponse handleRollbackBusiness(@RequestBody BusinessDTO businessDTO){
        LOGGER.info("请求参数：{}",businessDTO.toString());
        return businessService.handleRollbackBusiness(businessDTO);
    }
}
