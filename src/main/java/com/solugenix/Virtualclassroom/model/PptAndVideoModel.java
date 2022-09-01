package com.solugenix.Virtualclassroom.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PptAndVideoModel {

    private  String subject;
    private  String type ;
    private MultipartFile path;
}
