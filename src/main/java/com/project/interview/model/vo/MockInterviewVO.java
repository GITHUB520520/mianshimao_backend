package com.project.interview.model.vo;

import cn.hutool.json.JSONUtil;
import com.project.interview.model.entity.MockInterview;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class MockInterviewVO implements Serializable {

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


    private UserVO user;

    /**
     * 包装类转对象
     *
     * @param mockInterviewVO
     * @return
     */
    public static MockInterview voToObj(MockInterviewVO mockInterviewVO) {
        if (mockInterviewVO == null) {
            return null;
        }
        MockInterview mockInterview = new MockInterview();
        BeanUtils.copyProperties(mockInterviewVO, mockInterview);
        return mockInterview;
    }

    /**
     * 对象转包装类
     *
     * @param mockInterview
     * @return
     */
    public static MockInterviewVO objToVo(MockInterview mockInterview) {
        if (mockInterview == null) {
            return null;
        }
        MockInterviewVO mockInterviewVO = new MockInterviewVO();
        BeanUtils.copyProperties(mockInterview, mockInterviewVO);
        return mockInterviewVO;
    }
    
    private static final long serialVersionUID = 1L;
}
