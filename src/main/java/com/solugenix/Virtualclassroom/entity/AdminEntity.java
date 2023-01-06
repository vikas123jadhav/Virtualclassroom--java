package com.solugenix.Virtualclassroom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="ADMIN")

@SQLDelete(sql = "UPDATE ADMIN SET STATUS='deleted' WHERE ID=?")
@Where(clause = "STATUS <> 'deleted' ")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="ROLE_ID")
    private final Long  ROLE_ID=101L;

    @Column(name="USER_NAME")
    private String username;

    @Column(name="NAME")
    private String name;

    @Column(name="PASSWORD")
    private String password;

    @Column(name = "MOBILE_NO")
    private String mobileNo;

    @Column(name = "EMAIL")
    private String email;

    @Column(name="CREATED_DATE" ,updatable = false)
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date createdDate;

    @Column(name="STATUS")
    private String status="active";

    @Column(name="AGE")
    private Integer age;

    @Column(name="ROLE")
    private final String role="admin";

}
