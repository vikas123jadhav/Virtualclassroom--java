package com.solugenix.Virtualclassroom.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "PPT_VIDEO_DATA")

@SQLDelete(sql = "UPDATE PPT_VIDEO_DATA SET STATUS='deleted' WHERE PID=?")
@Where(clause = "STATUS <> 'deleted' ")
public class PptAndVideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long pId;


    @Column(name = "SUBJECT")
    private  String  subject;

    @Column(name="PPT_PATH")
    private  String path;

    @Column(name="TYPE")
    private String type;

    @Column(name = "STORED_DATE" ,updatable = false )
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date storedDate;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name="UPDATED_DATE" , insertable=false)
    private Date  updatedDate;

    @Column(name="STATUS")
    private String status="active";

}
