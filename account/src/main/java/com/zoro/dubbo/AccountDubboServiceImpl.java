package com.zoro.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoro.dto.AccountDTO;
import com.zoro.response.ObjectResponse;
import com.zoro.service.ITAccountService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description  Dubbo Api Impl
 */
@Service(version = "1.0.0",protocol = "${dubbo.protocol.id}",
         application = "${dubbo.application.id}",registry = "${dubbo.registry.id}",
         timeout = 3000)
@Slf4j
public class AccountDubboServiceImpl implements AccountDubboService {

    @Autowired
    private ITAccountService accountService;

    @Override
    public ObjectResponse decreaseAccount(AccountDTO accountDTO) {
        log.info("账户服务全局事务id:{}",RootContext.getXID());
        return accountService.decreaseAccount(accountDTO);
    }
}
