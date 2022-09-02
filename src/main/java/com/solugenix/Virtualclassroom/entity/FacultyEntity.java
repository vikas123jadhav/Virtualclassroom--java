package com.solugenix.Virtualclassroom.entity;

import lombok.Data;

import java.util.Date;

@Data
public class FacultyEntity {
    private Long fid;

    private String  fname;

    private String  userName;

    private String  subject;

    private String  mobileNo;

    private String deptno;

    private Date createdDate;

    private String status;

}
