package com.project.interview.model.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class VipExchangeRequest implements Serializable {

    /**
     * 会员兑换码
     */
    private String vipCode;

    private static final long serialVersionUID = 1L;
}
