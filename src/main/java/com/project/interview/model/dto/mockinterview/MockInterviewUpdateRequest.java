package com.project.interview.model.dto.mockinterview;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 编辑请求
 *
 *
 */
@Data
public class MockInterviewUpdateRequest implements Serializable {

    private Long id;

    /**
     * 工作年限
     */
    private String workExperience;

    /**
     * 工作岗位
     */
    private String jobPosition;

    /**
     * 面试难度
     */
    private String difficulty;

    /**
     * 消息列表（JSON 对象数组字段，同时包括了总结）
     */
    private String messages;

    /**
     * 状态（0-待开始、1-进行中、2-已结束）
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}