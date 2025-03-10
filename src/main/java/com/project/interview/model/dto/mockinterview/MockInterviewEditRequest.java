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
public class MockInterviewEditRequest implements Serializable {

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

    private static final long serialVersionUID = 1L;
}