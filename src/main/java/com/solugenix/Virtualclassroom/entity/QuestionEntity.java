package com.solugenix.Virtualclassroom.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="QUESTIONS")
@NoArgsConstructor

public class QuestionEntity {

    @Id
    @GeneratedValue( strategy= GenerationType.AUTO )
    private  Long qid;

    private  String subject;
    private  String question;
    private  Long askedId;

     @OneToMany( targetEntity = AnswerEntity.class, cascade = CascadeType.ALL)
     @JoinColumn(name="ans_que_fk" , referencedColumnName = "qid")
     private List<AnswerEntity> answerList;


    public QuestionEntity(Long qid,String subject, String question, Long askedId) {
        this.qid = qid;
        this.subject=subject;
        this.question = question;
        this.askedId = askedId;
    }

    public QuestionEntity(Long qid,String subject, String question, Long askedId, List<AnswerEntity> answerList) {
        this.qid = qid;
        this.subject=subject;
        this.question = question;
        this.askedId = askedId;
        this.answerList = answerList;
    }
}
