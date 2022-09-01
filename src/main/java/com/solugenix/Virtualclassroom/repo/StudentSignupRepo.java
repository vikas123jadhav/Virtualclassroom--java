package com.solugenix.Virtualclassroom.repo;

import com.solugenix.Virtualclassroom.entity.StudentSignupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface StudentSignupRepo extends JpaRepository<StudentSignupEntity, Serializable> {
}
