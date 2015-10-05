package com.springapp.mvc.controller.exam;

import com.springapp.mvc.domain.QueryUserDomain;
import com.springapp.mvc.domain.exam.*;
import com.springapp.mvc.pojo.User;
import com.springapp.mvc.pojo.exam.*;
import com.springapp.mvc.util.HibernateUtil;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by PTang_000 on 30-Sep-15.
 */
@Controller
@RequestMapping("/TDCS")
public class MarkingController {

    @Autowired
    QueryPaperDomain queryPaperDomain;

    @Autowired
    QueryExamResultDomain queryExamResultDomain;

    @Autowired
    QueryExamRecordDomain queryExamRecordDomain;

    @Autowired
    QueryUserDomain queryUserDomain;

    @Autowired
    QueryExamAnswerDomain queryExamAnswerDomain;

    @Autowired
    QueryMarkingRecord queryMarkingRecord;

    @RequestMapping(method = RequestMethod.GET, value = "/exam/marking")
    public String marking(ModelMap modelMap, Model model, HttpServletRequest request, HttpServletResponse response
//                         ,@RequestParam(value = "recordId") Integer recordId
            , @RequestParam(value = "resultId") Integer resultId){

//        ExamRecord examRecord = queryExamRecordDomain.getExamRecordById(recordId);
//        ExamResult examResult = queryExamResultDomain.getExamResultByExamRecord(examRecord);
//        modelMap.addAttribute("examRecord", examRecord);
//        modelMap.addAttribute("subjectiveScore", examResult.getSubjectiveScore());
        ExamResult examResult = queryExamResultDomain.getExamResultById(resultId);
        modelMap.addAttribute("examResult",examResult);

        return "marking";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/exam/marking/submit")
    @ResponseBody
    public void submitMarking(HttpServletRequest request, HttpServletResponse response
                              ,@RequestParam(value = "resultId",required = true)Integer resultId
                              ,@RequestParam(value = "markingRecord",required = true)JSONArray markingRecord
                              ,@RequestParam(value = "comment",required = false)String comment) throws Exception{

        ExamResult examResult = queryExamResultDomain.getExamResultById(resultId);
        User currentUser = queryUserDomain.getCurrentUser(request);
        examResult.setMarkedBy(currentUser);
        examResult.setComment(comment);
        examResult.setMarkedDate(new Date());

        Float subjectiveScore = (float)0;
        List<ExamAnswerRecord> examAnswerRecords = examResult.getExamRecord().getExamAnswerRecords();

        try{
            HibernateUtil.beginTransaction();

            for(int i = 0 ; i < markingRecord.length() ; i++){
                ExamMarkingRecord examMarkingRecord = new ExamMarkingRecord();

                examMarkingRecord.setMarkedBy(currentUser);

                for(ExamAnswerRecord e : examAnswerRecords){
                    if(e.getId().equals(markingRecord.getJSONObject(i).optInt("answerRecordId"))){
                        examMarkingRecord.setAnswerRecord(e);
                    }
                }
                Float score = (float)markingRecord.getJSONObject(i).optInt("score");
                examMarkingRecord.setMarkingScore(score);
                examMarkingRecord.setExamResult(examResult);
                queryMarkingRecord.saveMarkingRecord(examMarkingRecord);
                subjectiveScore += score;
            }
            examResult.setSubjectiveScore(subjectiveScore);

            queryExamResultDomain.updateExamResult(examResult);

            HibernateUtil.commitTransaction();
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }
}

