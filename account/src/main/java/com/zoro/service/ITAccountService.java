package com.zoro.service;

import com.baomidou.mybatisplus.service.IService;
import com.zoro.dto.AccountDTO;
import com.zoro.entity.TAccount;
import com.zoro.response.ObjectResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface ITAccountService extends IService<TAccount> {

    /**
     * 扣用户钱
     */
    ObjectResponse decreaseAccount(AccountDTO accountDTO);
}
