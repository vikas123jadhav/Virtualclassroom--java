package com.solugenix.Virtualclassroom.repo;


import com.solugenix.Virtualclassroom.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Set;

public interface AnswerRepo extends JpaRepository<AnswerEntity, Long> {

    @Query(value = "SELECT  ans_que_fk FROM answers  WHERE given_id=?",nativeQuery = true)
    public Set<Long> getQuestionIds(long given_id);

}
