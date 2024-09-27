package com.project.interview.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class BlackIpRequest implements Serializable {

    private String blackIp;

    public static final long serialVersionUID = 1L;
}
