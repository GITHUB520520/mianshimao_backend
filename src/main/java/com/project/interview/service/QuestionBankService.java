package com.project.interview.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.interview.model.dto.questionbank.QuestionBankQueryRequest;
import com.project.interview.model.entity.QuestionBank;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.interview.model.vo.QuestionBankVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author HP
* @description 针对表【question_bank(题库)】的数据库操作Service
* @createDate 2024-09-07 22:03:22
*/
public interface QuestionBankService extends IService<QuestionBank> {

    void validQuestionBank(QuestionBank questionBank, boolean b);

    QuestionBankVO getQuestionBankVO(QuestionBank questionBank, HttpServletRequest request);

    Wrapper<QuestionBank> getQueryWrapper(QuestionBankQueryRequest questionBankQueryRequest);

    Page<QuestionBankVO> getQuestionBankVOPage(Page<QuestionBank> questionBankPage, HttpServletRequest request);
}
