package com.solugenix.Virtualclassroom.controller.questionAndAnswer;


import com.solugenix.Virtualclassroom.entity.AnswerEntity;
import com.solugenix.Virtualclassroom.entity.QuestionEntity;
import com.solugenix.Virtualclassroom.exceptions.ResourceNotFoundException;
import com.solugenix.Virtualclassroom.repo.AnswerRepo;
import com.solugenix.Virtualclassroom.repo.QuestionRepository;
import com.solugenix.Virtualclassroom.service.questionAnswer.QuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class QuestionController {

    @Autowired
    private QuestionAnswerService questionAnswerService;

     @Autowired
     private QuestionRepository  questionRepository;

     @Autowired
     private AnswerRepo  answerRepo;


     @PostMapping("/storeQuestion")
    public ResponseEntity<?>  storeQuestion(@RequestBody QuestionEntity questionAnswerDTO){
           return  new ResponseEntity<>(questionRepository.save(questionAnswerDTO) , HttpStatus.OK);
     }

     @GetMapping("/getAllQuestions")
    public List<QuestionEntity> getAll(){
         return  questionRepository.findAll();
     }

     @GetMapping("/getQuestionListBySubject/{subject}")   // to show specific faculty based of their "subject"
     public List<QuestionEntity> getQuestions(@PathVariable("subject") String subject){
          return  questionRepository.findBySubjectEquals(subject);
     }


     @PostMapping("/storeAnswer")
      public ResponseEntity<?>  storeAnswer(@RequestBody QuestionEntity questionEntity) throws ResourceNotFoundException {
         QuestionEntity qa =
                 questionRepository.findById(questionEntity.getQid())
                         .orElseThrow(()->new ResourceNotFoundException("Question not found to give Answer :: " + questionEntity.getQid()));
        return  new ResponseEntity<>(questionRepository.save(questionEntity) , HttpStatus.OK);
     }


     @GetMapping("/getAskedQuestions/{askedId}")       // for getting questions list of specific Students
     public List<QuestionEntity>  getAskedQuestions(@PathVariable("askedId") Long askedId){
         return  questionRepository.findByAskedId(askedId);
     }

     @GetMapping("/getAnsweredQuestions/{givenId}")
    public List<QuestionEntity> getAnsweredQuestion(@PathVariable("givenId") Long givenId) throws ResourceNotFoundException {
          Set<Long> questionIds= answerRepo.getQuestionIds(givenId);
           ArrayList<QuestionEntity> qe = new ArrayList<>();
           for(Long qid: questionIds){
               qe.add(questionRepository.findById(qid)
                       .orElseThrow(()->new ResourceNotFoundException("Question not found to give Answer :: " + qid) ));
           }
          return  qe;
    }



    @GetMapping("/getAllAnswerList")
    public List<AnswerEntity> getAllAnswers(){
         return questionAnswerService.fetchAllAnswers();
    }

    @GetMapping("/getAllQuestionList")
    public List<QuestionEntity> getAllQuestions(){
        return questionAnswerService.fetchAllQuestions();
    }

    @GetMapping("/deleteAnswer/{id}")
    public boolean  deleteAnswerById(@PathVariable("id") Long id) throws ResourceNotFoundException {
            return questionAnswerService.deleteAnswer(id);
    }

    @GetMapping("/deleteQuestion/{id}")
    public boolean  deleteQuestionById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return questionAnswerService.deleteQuestion(id);
    }

}
