package com.project.interview.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.interview.model.dto.post.PostQueryRequest;
import com.project.interview.model.dto.question.QuestionQueryRequest;
import com.project.interview.model.entity.Post;
import com.project.interview.model.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.interview.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author HP
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2024-09-07 22:03:22
*/
public interface QuestionService extends IService<Question> {

    void validQuestion(Question question, boolean b);

    QuestionVO getQuestionVO(Question question, HttpServletRequest request);

    Wrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);

    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request);

    Page<Question> listQuestionByPage(QuestionQueryRequest questionQueryRequest);

    /**
     * 从 ES 查询
     *
     * @param questionQueryRequest
     * @return
     */
    Page<Question> searchFromEs(QuestionQueryRequest questionQueryRequest);
}
