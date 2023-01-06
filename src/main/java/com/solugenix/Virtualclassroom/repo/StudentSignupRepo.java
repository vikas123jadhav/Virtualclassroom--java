package com.solugenix.Virtualclassroom.repo;


import com.solugenix.Virtualclassroom.entity.StudentSignupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

public interface StudentSignupRepo extends JpaRepository<StudentSignupEntity, Serializable> {

    @Query(value = "SELECT * FROM student_sign_up WHERE user_name=? AND password=?",nativeQuery = true)
    public StudentSignupEntity findByUserNameAndPassword(String username, String password);

    public StudentSignupEntity findByUsernameEquals(String username);
}
