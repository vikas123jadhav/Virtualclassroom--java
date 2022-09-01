package com.solugenix.Virtualclassroom.repo;

import com.solugenix.Virtualclassroom.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

public interface AdminRepo extends JpaRepository<AdminEntity, Serializable> {

    @Query(value = "SELECT * FROM admin WHERE user_name=? AND password=?",nativeQuery = true)
    public AdminEntity findByUserNameAndPassword(String username, String password);
}
