package com.solugenix.Virtualclassroom.service;

import com.solugenix.Virtualclassroom.entity.*;
import com.solugenix.Virtualclassroom.exceptions.NoDataFoundException;

import java.util.List;

public interface IAdminServiceMgmt {

    public boolean  checkAdminAuth(String username ,String password);
    public AdminEntity createAdmin(AdminEntity admin);

    public PptAndVideoEntity storePptOrVideo(PptAndVideoEntity pptOrVideo);

    public FacultySignUpEntity createFaculty(FacultySignUpEntity faculty);

    public List<AdminEntity> fetchAllAdmin()throws Exception;
    public List<FacultyEntity> fetchAllfaculty()throws Exception;
    public List<StudentEntity> fetchAllStudents()throws Exception;


    public List<PptAndVideoEntity> fetchAllPpts(String type)throws Exception;
    public List<PptAndVideoEntity> fetchAllVideos(String type)throws Exception;
    public PptAndVideoEntity getPptAndVideoById(int pid) throws NoDataFoundException;
    public List<PptAndVideoEntity> fetchBothPptAndVideos() throws Exception;


    public String deletePptORVideoById(Long id);

    public String updatePptOrVideo(PptAndVideoEntity pptOrVideo);


    public String deleteStudentAccById(Long id);
    public String deleteFacultyAccById(Long id);
    public String deleteAdminAccById(Long id);

}
