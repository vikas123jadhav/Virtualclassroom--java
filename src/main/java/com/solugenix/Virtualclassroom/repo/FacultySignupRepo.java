package com.solugenix.Virtualclassroom.repo;

import com.solugenix.Virtualclassroom.entity.FacultySignUpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

public interface FacultySignupRepo extends JpaRepository<FacultySignUpEntity , Serializable> {

    public FacultySignUpEntity  findByUsernameEquals(String username);


    @Query(value = "SELECT * FROM faculty_sign_up WHERE user_name=? AND password=?",nativeQuery = true)
    public FacultySignUpEntity findByUsernameAndPassword(String username, String password);

    public FacultySignUpEntity findByUsername(String username);


}
