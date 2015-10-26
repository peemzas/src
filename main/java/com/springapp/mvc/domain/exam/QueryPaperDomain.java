package com.springapp.mvc.domain.exam;

import com.springapp.mvc.pojo.Position;
import com.springapp.mvc.pojo.User;
import com.springapp.mvc.pojo.exam.*;
import com.springapp.mvc.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Phuthikorn_T on 8/11/2015.
 */
@Service
public class QueryPaperDomain extends HibernateUtil {

    @Autowired
    QueryStatusDomain queryStatusDomain;

    public ExamPaper getPaperById(Integer paperId) {
        Criteria criteria = getSession().createCriteria(ExamPaper.class);
        criteria.add(Restrictions.eq("id", paperId));

        return (ExamPaper) criteria.uniqueResult();
    }

//    Add By Mr.Wanchana
    public void createPaper(ExamPaper examPaper, List<Integer> qIds, List<Float> newScores){

        HibernateUtil.beginTransaction();
        getSession().save(examPaper);

        QueryQuestionDomain queryQuestionDomain = new QueryQuestionDomain();
        for(int i = 0; i < qIds.size(); i++){
            PaperQuestion paperQuestion = new PaperQuestion();
            paperQuestion.setExamPaper(examPaper);
            paperQuestion.setQuestion(queryQuestionDomain.getQuestionById(qIds.get(i)));
            paperQuestion.setScore(newScores.get(i));
            getSession().save(paperQuestion);
        }

        HibernateUtil.commitTransaction();
        HibernateUtil.closeSession();
    }

//    public void createPaperQuestion(ExamPaper examPaper, List<Integer> qIds, List<Float> newScores){
//
//        HibernateUtil.beginTransaction();
//        QueryQuestionDomain queryQuestionDomain = new QueryQuestionDomain();
//        for(int i = 0; i < qIds.size(); i++){
//            PaperQuestion paperQuestion = new PaperQuestion();
//            paperQuestion.setExamPaper(examPaper);
//            paperQuestion.setQuestion(queryQuestionDomain.getQuestionById(qIds.get(i)));
//            paperQuestion.setScore(new BigDecimal(newScores.get(i)));
//            getSession().save(paperQuestion);
//        }
//        HibernateUtil.commitTransaction();
//    }

    public void updatePaper(List<Integer> qIds, List<Float> newScores, Integer paperId, User updateBy, String paperCode, String paperName, Float paperMaxScore, Date updateDate, Integer paperTime, Status paperStatus, Position paperForPosition){

        QueryPaperDomain queryPaperDomain = new QueryPaperDomain();
        ExamPaper examPaper = queryPaperDomain.getPaperById(paperId);

        HibernateUtil.beginTransaction();
        Criteria criteria = getSession().createCriteria(PaperQuestion.class);
        criteria.add(Restrictions.eq("pk.examPaper", examPaper));
        List<PaperQuestion> paperQuestions = criteria.list();
        for(int i = 0; i < paperQuestions.size(); i++){
            getSession().delete(paperQuestions.get(i));
        }

//        createPaperQuestion(examPaper, qIds, newScores);
        QueryQuestionDomain queryQuestionDomain = new QueryQuestionDomain();
        for(int i = 0; i < qIds.size(); i++){
            PaperQuestion paperQuestion = new PaperQuestion();
            paperQuestion.setExamPaper(examPaper);
            paperQuestion.setQuestion(queryQuestionDomain.getQuestionById(qIds.get(i)));
            paperQuestion.setScore(newScores.get(i));
            getSession().save(paperQuestion);
        }

        examPaper.setUpdateBy(updateBy);
        examPaper.setCode(paperCode);
        examPaper.setName(paperName);
        examPaper.setMaxScore(paperMaxScore);
        examPaper.setUpdateDate(updateDate);
        examPaper.setTimeLimit(paperTime);
        examPaper.setPaperStatus(paperStatus);

        if(paperForPosition == null) {
            examPaper.setPosition(paperForPosition);
        }

        getSession().merge(examPaper);

        HibernateUtil.commitTransaction();
        HibernateUtil.closeSession();
    }

    public List<ExamPaper> getAllPapers(){
        Criteria criteria = getSession().createCriteria(ExamPaper.class);
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("id"), "id")
                .add(Projections.property("name"), "name")
                .add(Projections.property("createDate"), "createDate")
                .add(Projections.property("createBy"), "createBy")
                .add(Projections.property("maxScore"), "maxScore")
                .add(Projections.property("code"), "code")
                .add(Projections.property("updateDate"), "updateDate")
                .add(Projections.property("updateBy"), "updateBy")
                .add(Projections.property("timeLimit"), "timeLimit")
                .add(Projections.property("position"), "position")
                .add(Projections.property("paperStatus"), "paperStatus"));
        criteria.addOrder(Order.asc("id"));
        criteria.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<ExamPaper> papers = criteria.list();

        return papers;
    }

    public void deletePaper(List paperId){

        QueryPaperDomain queryPaperDomain = new QueryPaperDomain();

        HibernateUtil.beginTransaction();
        try{
            for(int i = 0; i < paperId.size(); i++){
                ExamPaper examPaper = queryPaperDomain.getPaperById((Integer) paperId.get(i));
                getSession().delete(examPaper);
            }
            HibernateUtil.commitTransaction();

        }catch(Exception e){
            System.out.println("==========Error while delete papers==========");
            e.printStackTrace();
        }finally {
            HibernateUtil.closeSession();
        }
    }

//    public void deletePaperQuestionByExamPaper(ExamPaper examPaper){
//
//        HibernateUtil.beginTransaction();
//        Criteria criteria = getSession().createCriteria(PaperQuestion.class);
//        criteria.add(Restrictions.eq("pk.examPaper", examPaper));
//        List<PaperQuestion> paperQuestions = criteria.list();
//        for(int i = 0; i < paperQuestions.size(); i++){
//            getSession().delete(paperQuestions.get(i));
//        }
//        HibernateUtil.commitTransaction();
//        HibernateUtil.closeSession();
//    }

    public void updatePaperStatus(ExamPaper examPaper){

        HibernateUtil.beginTransaction();
        getSession().merge(examPaper);
        HibernateUtil.commitTransaction();
        HibernateUtil.closeSession();
    }

    public List<ExamPaper> generalSearchPaper(List empIds, String code, String name){

        Criteria criteria = getSession().createCriteria(ExamPaper.class);
        if(empIds != null){
            criteria.add(Restrictions.in("createBy.userId", empIds));
        }
        if(!code.equals("")){
            criteria.add(Restrictions.like("code", "%" + code + "%").ignoreCase());
        }
        if(!name.equals("")){
            criteria.add(Restrictions.like("name", "%" + name + "%").ignoreCase());
        }
        criteria.addOrder(Order.asc("id"));
        List<ExamPaper> papers = criteria.list();

        return papers;
    }

    public List<ExamPaper> advanceSearchPaper(List empIds, String code, String name, String createDateFrom, String createDateTo, String scoreFrom, String scoreTo, String paperStatus) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Date dateFrom = null;
        Date dateTo = null;
        if (!createDateFrom.equals("")){
            dateFrom = simpleDateFormat.parse(createDateFrom);
        }
        if (!createDateTo.equals("")){
            dateTo = simpleDateFormat.parse(createDateTo);
        }
        Float sFrom = null;
        Float sTo = null;

        if (!scoreFrom.equals("")) {
            sFrom = new Float(scoreFrom);
        }
        if (!scoreTo.equals("")) {
            sTo = new Float(scoreTo);
        }

        Criteria criteria = getSession().createCriteria(ExamPaper.class);
        if (empIds != null){
            criteria.add(Restrictions.in("createBy.id", empIds));
        }
        if (!code.equals("")){
            criteria.add(Restrictions.like("code", "%" + code + "%").ignoreCase());
        }
        if (!name.equals("")){
            criteria.add(Restrictions.like("name", "%" + name + "%").ignoreCase());
        }
        if (!createDateFrom.equals("")) {
            criteria.add(Restrictions.ge("createDate", dateFrom));
        }
        if (!createDateTo.equals("")) {
            criteria.add(Restrictions.le("createDate", dateTo));
        }
        if (!scoreFrom.equals("")) {
            criteria.add(Restrictions.ge("maxScore", sFrom));
        }
        if (!scoreTo.equals("")) {
            criteria.add(Restrictions.le("maxScore", sTo));
        }
        if(!paperStatus.equals("")){
            Integer pStatus = new Integer(paperStatus);
            if(pStatus != 0){
                criteria.add(Restrictions.eq("paperStatus.id", new Integer(paperStatus)));
            }
        }
        criteria.addOrder(Order.asc("id"));
        List<ExamPaper> papers = criteria.list();

        return papers;
    }

    public List<ExamPaper> getOpenedPaperForPosition(Position position) {
        Criteria criteria = getSession().createCriteria(ExamPaper.class);

        criteria.add(Restrictions.eq("paperStatus",queryStatusDomain.getOpenStatus()));

        Criterion c1 = Restrictions.isNull("position");
        Criterion c2 = Restrictions.eq("position", position);

        criteria.add(Restrictions.or(c1,c2));

        return criteria.list();
    }

    public List getCode(Integer paperId){

        Criteria criteria = getSession().createCriteria(ExamPaper.class);
        criteria.add(Restrictions.ne("id", paperId));
        criteria.setProjection(Projections.projectionList().add(Projections.property("code"), "code"));

        List codes = criteria.list();

        return codes;
    }
}
