package com.solugenix.Virtualclassroom.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="STUDENT_SIGN_UP")

@SQLDelete(sql = "UPDATE STUDENT_SIGN_UP SET STATUS='deleted' WHERE ID=?")
@Where(clause = "STATUS <> 'deleted' ")
public class StudentSignupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private  Long id;

    @Column(name="ROLE_ID")
    private final long  roleid=303L;

    @Column(name = "NAME")
    private  String name;

    @Column(name = "USER_NAME")
    private  String username;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "MOBILE_NO")
    private String mobileNo;

    @Column(name="AGE")
    private Integer age;


    @Column(name = "PASSWORD")
    private  String password;

    @Column(name="CREATED_DATE"  , updatable=false)
    @Temporal(TemporalType.DATE )
    @CreationTimestamp
    private Date createdDate;


    @Column(name="STATUS")
    private String status="active";

    @Column(name="ROLE")
    private final String role="student";

}
