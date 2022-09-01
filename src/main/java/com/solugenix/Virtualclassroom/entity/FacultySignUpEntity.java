package com.solugenix.Virtualclassroom.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="FACULTY_SIGN_UP")
@Data

@SQLDelete(sql = "UPDATE FACULTY_SIGN_UP SET STATUS='deleted' WHERE FID=?")
@Where(clause = "STATUS <> 'deleted' ")
public class FacultySignUpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fId;

    @Column(name = "F_NAME")
    private String  fname;

    @Column(name = "USER_NAME")
    private String  userName;

    @Column(name = "PASSWORD")
    private String  password;

    @Column(name="SUBJECT")
    private String  subject;

    @Column(name="MOBILE_NO")
    private String  mobileNo;

    @Column(name="DEPTNO")
    private String deptno;

    @Column(name="CREATED_DATE"  , updatable=false)
    @Temporal(TemporalType.DATE )
    @CreationTimestamp
    private Date createdDate;

    @Column(name="STATUS")
    private String status="active";
}
