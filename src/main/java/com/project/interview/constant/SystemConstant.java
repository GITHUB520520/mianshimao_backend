package com.project.interview.constant;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import com.project.interview.model.dto.questionbank.QuestionBankQueryRequest;

public interface SystemConstant {

    /**
     * Redis Key 前缀
     */
    String SYSTEM_REDIS_KEY_PREFIX = "mianshiya:getQuestionVO";

    /**
     * 获取 Redis key
     *
     * @param questionBankQueryRequest
     * @return
     */
    static String getRedisKey(QuestionBankQueryRequest questionBankQueryRequest) {
        String str = JSONUtil.toJsonStr(questionBankQueryRequest);
        String key = DigestUtil.md5Hex(str);
        return String.format("%s:%s", SYSTEM_REDIS_KEY_PREFIX, key);
    }

}
