package com.solugenix.Virtualclassroom.entity;

import lombok.Data;

import java.util.Date;

@Data
public class StudentEntity {


    private  Long id;

    private  String name;
    private String email;
    private String mobileNo;
    private   String role;
    private Integer age;
    private  String username;

    private String password;

    private Date createdDate;

    private String status;
}
