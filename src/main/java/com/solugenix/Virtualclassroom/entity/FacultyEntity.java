package com.solugenix.Virtualclassroom.entity;

import lombok.Data;

import java.util.Date;

@Data
public class FacultyEntity {
    private Long id;
    private String  name;
    private String role;
    private String  username;

    private Integer age;
    private String  subject;

    private String  mobileNo;

    private String deptno;
    private String email;

    private Date createdDate;

    private String status;

}
