package com.zoro.dubbo;

import com.zoro.dto.AccountDTO;
import com.zoro.response.ObjectResponse;

/**
 * @Description  账户服务接口
 */
public interface AccountDubboService {

    /**
     * 从账户扣钱
     */
    ObjectResponse decreaseAccount(AccountDTO accountDTO);
}
