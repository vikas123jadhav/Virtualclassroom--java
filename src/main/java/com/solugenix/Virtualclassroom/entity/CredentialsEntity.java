package com.solugenix.Virtualclassroom.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="CREDENTIALS")
@Data

@SQLDelete(sql = "UPDATE CREDENTIALS SET STATUS='deleted' WHERE ACC_ID=?")
@Where(clause = "STATUS <> 'deleted' ")

public class CredentialsEntity  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CID")
    private  Long credentialsId;


    @Column(name = "NAME")
    private  String name;

    @Column(name="ROLE_ID")
    private  long  roleid;

    @Column(name="ACC_ID")
    private  long  accId;

    @Column(name = "USER_NAME")
    private  String username;


    @Column(name = "PASSWORD")
    private  String password;

    @Column(name="STATUS")
    private String status;

    @Column(name="ROLE")
    private  String role;

}
