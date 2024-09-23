package com.project.interview.constant;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import com.project.interview.model.dto.question.QuestionQueryRequest;
import com.project.interview.model.dto.questionbank.QuestionBankQueryRequest;

public interface SystemConstant {

    /**
     * Redis Key 前缀
     */
    String SYSTEM_REDIS_KEY_PREFIX = "mianshiya:getQuestionVO";

    String SYSTEM_LIST_QUESTION_KEY = "mianshiya:listQuestionVOByPage";

    /**
     * 获取 Redis key
     *
     * @param questionBankQueryRequest
     * @return
     */
    static String getQuestionVORedisKey(QuestionBankQueryRequest questionBankQueryRequest) {
        String str = JSONUtil.toJsonStr(questionBankQueryRequest);
        String key = DigestUtil.md5Hex(str);
        return String.format("%s:%s", SYSTEM_REDIS_KEY_PREFIX, key);
    }

    /**
     * 获取 Redis key
     * @param questionQueryRequest
     * @return
     */
    static String getListQuestionVOByPageRedisKey(QuestionQueryRequest questionQueryRequest) {
        String str = JSONUtil.toJsonStr(questionQueryRequest);
        String key = DigestUtil.md5Hex(str);
        return String.format("%s:%s", SYSTEM_LIST_QUESTION_KEY, key);
    }
}
