package com.solugenix.Virtualclassroom.repo;

import com.solugenix.Virtualclassroom.entity.AdminEntity;
import com.solugenix.Virtualclassroom.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface SubjectRepo extends JpaRepository<SubjectEntity, Serializable> {
}
