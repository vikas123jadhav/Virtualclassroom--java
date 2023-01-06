package com.solugenix.Virtualclassroom.repo;

import com.solugenix.Virtualclassroom.entity.CredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;


public interface CredentialsEntityRepo extends JpaRepository<CredentialsEntity, Serializable> {

    public CredentialsEntity findByUsername(String username);

//    @Query(value = "DELETE FROM credentials WHERE acc_id=?",nativeQuery = true)
//    public  Long deleteByAccId(Long accId);

    @Modifying
    @Transactional           // ->  optional to here , we can place either in Service class
    @Query("DELETE FROM CredentialsEntity WHERE   acc_id=?1")
    public  int  deleteByAccId(long  accId);

    public CredentialsEntity findByAccId(long accId);

}
