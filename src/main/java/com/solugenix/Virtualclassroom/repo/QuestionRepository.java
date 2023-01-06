package com.solugenix.Virtualclassroom.repo;

import com.solugenix.Virtualclassroom.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    public List<QuestionEntity> findBySubjectEquals(String subject);

    public  List<QuestionEntity> findByAskedId(Long askedId);
}
