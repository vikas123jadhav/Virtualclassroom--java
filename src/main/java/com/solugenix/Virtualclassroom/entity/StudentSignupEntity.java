package com.solugenix.Virtualclassroom.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="STUDENT_SIGN_UP")

@SQLDelete(sql = "UPDATE STUDENT_SIGN_UP SET STATUS='deleted' WHERE SID=?")
@Where(clause = "STATUS <> 'deleted' ")
public class StudentSignupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SID")
    private  Long sid;

    @Column(name = "S_NAME")
    private  String sname;

    @Column(name = "USER_NAME")
    private  String username;

    @Column(name = "MOBILE_NO")
    private String mobileNo;

    @Column(name = "PASSWORD")
    private  String password;

    @Column(name="CREATED_DATE"  , updatable=false)
    @Temporal(TemporalType.DATE )
    @CreationTimestamp
    private Date createdDate;


    @Column(name="STATUS")
    private String status="active";

}
