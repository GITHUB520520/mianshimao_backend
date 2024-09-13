package com.project.interview.mapper;

import com.project.interview.model.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
* @author HP
* @description 针对表【question(题目)】的数据库操作Mapper
* @createDate 2024-09-07 22:03:22
* @Entity com.project.interview.model.entity.Question
*/
public interface QuestionMapper extends BaseMapper<Question> {

    @Select("select * from question where updateTime >= #{fiveMinutesAgoDate}")
    List<Question> listQuestionWithDelete(Date fiveMinutesAgoDate);
}




