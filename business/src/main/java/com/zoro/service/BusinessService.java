package com.zoro.service;

import com.zoro.dto.BusinessDTO;
import com.zoro.response.ObjectResponse;

/**
 */
public interface BusinessService {

    ObjectResponse handleBusiness(BusinessDTO businessDTO);

    ObjectResponse handleRollbackBusiness(BusinessDTO businessDTO);
}
