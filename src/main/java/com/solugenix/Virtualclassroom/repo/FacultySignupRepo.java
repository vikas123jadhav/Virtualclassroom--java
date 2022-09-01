package com.solugenix.Virtualclassroom.repo;

import com.solugenix.Virtualclassroom.entity.FacultySignUpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface FacultySignupRepo extends JpaRepository<FacultySignUpEntity , Serializable> {
}
