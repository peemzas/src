package com.springapp.mvc.pojo.exam;

import com.springapp.mvc.pojo.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Phuthikorn_T on 7/1/2015.
 */

@Entity
@Table(name="TDCS_EXAM_RESULTS")
public class ExamResult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "TDCS_EXAM_RESULT_ID_SEQ_GEN")
    @SequenceGenerator(name = "TDCS_EXAM_RESULT_ID_SEQ_GEN", sequenceName = "TDCS_EXAM_RESULT_SEQ")
    @Column(name = "RESULT_ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "MARKED_BY",referencedColumnName = "USER_ID")
    private  User markedBy;

    @Column(name = "COMMENTING")
    private  String comment;

    @Column(name = "RESULT_SCORE")
    private Integer resultScore;

    @ManyToOne
    @JoinColumn(name = "EXAM_RECORD_ID")
    private ExamRecord examRecordId;




    public ExamRecord getExamRecordId() {
        return examRecordId;
    }

    public void setExamRecordId(ExamRecord examRecordId) {
        this.examRecordId = examRecordId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getMarkedBy() {
        return markedBy;
    }

    public void setMarkedBy(User markedBy) {
        this.markedBy = markedBy;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getResultScore() {
        return resultScore;
    }

    public void setResultScore(Integer resultScore) {
        this.resultScore = resultScore;
    }
}
