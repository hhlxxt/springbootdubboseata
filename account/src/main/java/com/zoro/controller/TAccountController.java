package com.zoro.controller;

import com.zoro.dto.AccountDTO;
import com.zoro.response.ObjectResponse;
import com.zoro.service.ITAccountService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 账户扣钱
 * </p>
 *
 */
@RestController
@RequestMapping("/account")
@Slf4j
public class TAccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TAccountController.class);

    @Autowired
    private ITAccountService accountService;

    @PostMapping("/dec_account")
    ObjectResponse decreaseAccount(@RequestBody AccountDTO accountDTO) {
        LOGGER.info("请求账户微服务：{}", accountDTO.toString());
        return accountService.decreaseAccount(accountDTO);
    }
}

