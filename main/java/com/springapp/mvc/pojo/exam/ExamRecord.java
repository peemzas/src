package com.springapp.mvc.pojo.exam;

import com.springapp.mvc.pojo.User;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Phuthikorn_T on 8/5/2015.
 */
@Entity
@Table(name = "TDCS_EXAM_RECORDS")
public class ExamRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RECORD_ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "PAPER_ID")
    private ExamPaper paper;

    @Column(name = "EXAM_DATE")
    private Date examDate;

    @Column(name = "TIME_TAKEN_MINUTE")
    private Integer timeTaken;

    @OneToMany(mappedBy = "examRecord",fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<ExamAnswerRecord> examAnswerRecords;

    @Column(name = "IS_PRE_TEST")
    @Type(type = "true_false")
    private Boolean isPreTest;

    public Boolean getIsPreTest() {
        return isPreTest;
    }

    public void setIsPreTest(Boolean isPreTest) {
        this.isPreTest = isPreTest;
    }


    public List<ExamAnswerRecord> getExamAnswerRecords() {
        return examAnswerRecords;
    }

    public void setExamAnswerRecords(List<ExamAnswerRecord> examAnswerRecords) {
        this.examAnswerRecords = examAnswerRecords;
    }

    public Integer getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Integer timeTaken) {
        this.timeTaken = timeTaken;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer recordId) {
        this.id = recordId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
    }

    public ExamPaper getPaper() {
        return paper;
    }

    public void setPaper(ExamPaper paperId) {
        this.paper = paperId;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }
}
