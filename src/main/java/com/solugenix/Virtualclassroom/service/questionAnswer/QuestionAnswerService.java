package com.solugenix.Virtualclassroom.service.questionAnswer;

import com.solugenix.Virtualclassroom.entity.AnswerEntity;
import com.solugenix.Virtualclassroom.entity.QuestionEntity;
import com.solugenix.Virtualclassroom.entity.SubjectEntity;
import com.solugenix.Virtualclassroom.exceptions.ResourceNotFoundException;
import com.solugenix.Virtualclassroom.repo.AnswerRepo;
import com.solugenix.Virtualclassroom.repo.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class QuestionAnswerService {

    @Autowired
    private AnswerRepo answerRepo;

    @Autowired
    private QuestionRepository questionRepo;


    public List<QuestionEntity> fetchAllQuestions(){
        return  questionRepo.findAll();
    }

    public List<AnswerEntity> fetchAllAnswers(){
        return  answerRepo.findAll();
    }

     public boolean deleteQuestion(Long id) throws ResourceNotFoundException {
        QuestionEntity questionEntity = questionRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("ID related No Question Found to Delete ::"+ id));
           questionRepo.delete(questionEntity);
           if(questionEntity!=null) return  true;
           else  return  false;
     }

    public boolean deleteAnswer(Long id) throws ResourceNotFoundException {
        AnswerEntity answerEntity = answerRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("ID related No Question Found to Delete ::"+ id));
        answerRepo.delete(answerEntity);
        if(answerEntity!=null) return  true;
        else  return  false;
    }
}
