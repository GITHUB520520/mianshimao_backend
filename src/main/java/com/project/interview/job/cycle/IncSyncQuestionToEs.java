//package com.project.interview.job.cycle;
//
//import cn.hutool.core.collection.CollUtil;
//import com.project.interview.esdao.QuestionEsDao;
//import com.project.interview.mapper.QuestionMapper;
//import com.project.interview.model.dto.question.QuestionEsDTO;
//import com.project.interview.model.entity.Question;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import javax.annotation.Resource;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * 增量同步帖子到 es
// *
// *
// */
//// todo 取消注释开启任务
////@Component
//@Slf4j
//@Deprecated
//public class IncSyncQuestionToEs {
//
//    @Resource
//    private QuestionMapper questionMapper;
//
//    @Resource
//    private QuestionEsDao questionEsDao;
//
//    /**
//     * 每分钟执行一次
//     */
//    @Scheduled(fixedRate = 60 * 1000)
//    public void run() {
//        // 查询近 5 分钟内的数据
//        Date fiveMinutesAgoDate = new Date(new Date().getTime() - 5 * 60 * 1000L);
//        List<Question> questionList = questionMapper.listQuestionWithDelete(fiveMinutesAgoDate);
//        if (CollUtil.isEmpty(questionList)) {
//            log.info("no inc question");
//            return;
//        }
//        List<QuestionEsDTO> questionEsDTOList = questionList.stream()
//                .map(QuestionEsDTO::objToDto)
//                .collect(Collectors.toList());
//        final int pageSize = 500;
//        int total = questionEsDTOList.size();
//        log.info("IncSyncQuestionToEs start, total {}", total);
//        for (int i = 0; i < total; i += pageSize) {
//            int end = Math.min(i + pageSize, total);
//            log.info("sync from {} to {}", i, end);
//            questionEsDao.saveAll(questionEsDTOList.subList(i, end));
//        }
//        log.info("IncSyncQuestionToEs end, total {}", total);
//    }
//}
