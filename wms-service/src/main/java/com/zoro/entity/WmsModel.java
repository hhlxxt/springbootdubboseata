package com.zoro.entity;

import lombok.Data;

import java.util.Date;
@Data
public class WmsModel {

    private int wmsId;
    private String orderId;
    private String address;
    private String wmsStatus;
    private Date createTime;
    private Date updateTime;
}
