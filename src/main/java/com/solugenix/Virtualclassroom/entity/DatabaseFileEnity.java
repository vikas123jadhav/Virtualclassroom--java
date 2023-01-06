package com.solugenix.Virtualclassroom.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "files")


@SQLDelete(sql = "UPDATE FILES SET STATUS='deleted' WHERE ID=?")
@Where(clause = "STATUS <> 'deleted' ")
public class DatabaseFileEnity {
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String subject;

    private String type;

    @Transient
    private MultipartFile multipartFile;
    private String fileName;

    private String fileType;

    private String uploadedByUsername;
    private String uploadedId;
    private String uploadedRole;

    @Lob

    private byte[] data;


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

    // to store with uploader details
    public DatabaseFileEnity(String fileName, String fileType,  String subject,
                             String type,String uploadedId,String uploadedByUsername,String uploadedRole) {

        this.fileName = fileName;
        this.fileType = fileType;
        this.subject=subject;
        this.type=type;
        this.uploadedId=uploadedId;
        this.uploadedByUsername=uploadedByUsername;
        this.uploadedRole=uploadedRole;
    }

    public DatabaseFileEnity(String fileName, String fileType,  String subject, String type) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.subject=subject;
        this.type=type;

    }
    public DatabaseFileEnity(String fileName, String fileType, byte[] data , String subject, String type) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.subject=subject;
        this.type=type;
    }

    // for update
    public DatabaseFileEnity(String fileName, String fileType, byte[] data , String subject, String type,Long id
            ,String uploadedId,String uploadedByUsername,String uploadedRole) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.subject=subject;
        this.type=type;
        this.id=id;
        this.uploadedId=uploadedId;
        this.uploadedByUsername=uploadedByUsername;
        this.uploadedRole=uploadedRole;
    }

}
