package com.springapp.mvc.pojo.exam;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Phuthikorn_T on 15-Sep-15.
 */
@Entity
@Table(name = "TDCS_PAPER_QUESTION")
@AssociationOverrides({
        @AssociationOverride(name ="pk.examPaper", joinColumns = @JoinColumn(name ="PAPER_ID")),
        @AssociationOverride(name ="pk.question", joinColumns = @JoinColumn(name ="QUESTION_ID"))
})
public class PaperQuestion implements Serializable {

    @EmbeddedId
    private PaperQuestionPk pk = new PaperQuestionPk();

    @Column(name = "SCORE")
    private BigDecimal score;

    public PaperQuestionPk getPk() {
        return pk;
    }

    public void setPk(PaperQuestionPk pk) {
        this.pk = pk;
    }

    @Transient
    public ExamPaper getExamPaper(){
        return pk.getExamPaper();
    }
    public void setExamPaper(ExamPaper ep){
        pk.setExamPaper(ep);
    }

    @Transient
    public Question getQuestion(){
        return pk.getQuestion();
    }
    public void setQuestion(Question q){
        pk.setQuestion(q);
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
