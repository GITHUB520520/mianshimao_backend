package com.project.interview.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.interview.model.dto.questionbankquestion.QuestionBankQuestionQueryRequest;
import com.project.interview.model.entity.QuestionBankQuestion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.interview.model.entity.User;
import com.project.interview.model.vo.QuestionBankQuestionVO;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author HP
* @description 针对表【question_bank_question(题库题目)】的数据库操作Service
* @createDate 2024-09-07 22:03:22
*/
public interface QuestionBankQuestionService extends IService<QuestionBankQuestion> {

    void validQuestionBankQuestion(QuestionBankQuestion questionBankQuestion, boolean b);

    Wrapper<QuestionBankQuestion> getQueryWrapper(QuestionBankQuestionQueryRequest questionBankQuestionQueryRequest);

    Page<QuestionBankQuestionVO> getQuestionBankQuestionVOPage(Page<QuestionBankQuestion> questionBankQuestionPage, HttpServletRequest request);

    QuestionBankQuestionVO getQuestionBankQuestionVO(QuestionBankQuestion questionBankQuestion, HttpServletRequest request);

    @Transactional(rollbackFor = Exception.class)
    void batchAddQuestionsToBank(List<Long> questionIdList, Long questionBankId, User loginUser);

    @Transactional(rollbackFor = Exception.class)
    void batchRemoveQuestionsFromBank(List<Long> questionIdList, Long questionBankId);

    @Transactional(rollbackFor = Exception.class)
    void batchDeleteQuestions(List<Long> questionIdList);

    @Transactional(rollbackFor = Exception.class)
    void batchAddQuestionsToBankInner(List<QuestionBankQuestion> questionBankQuestionList);
}
