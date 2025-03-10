package com.project.interview.model.dto.mockinterview;

import com.project.interview.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 查询请求
 *
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MockInterviewQueryRequest extends PageRequest implements Serializable {

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

    /**
     * 创建人（用户 id）
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}