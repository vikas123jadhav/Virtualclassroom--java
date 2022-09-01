package com.solugenix.Virtualclassroom.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="ADMIN")

@SQLDelete(sql = "UPDATE ADMIN SET STATUS='deleted' WHERE AID=?")
@Where(clause = "STATUS <> 'deleted' ")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long aid;

    @Column(name="USER_NAME")
    private String username;

    @Column(name="NAME")
    private String name;

    @Column(name="PASSWORD")
    private String password;

    @Column(name = "MOBILE_NO")
    private String mobileNo;

    @Column(name="CREATED_DATE" ,updatable = false)
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date createdDate;

    @Column(name="STATUS")
    private String status="active";

}
