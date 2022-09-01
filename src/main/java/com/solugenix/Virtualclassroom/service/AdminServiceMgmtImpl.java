package com.solugenix.Virtualclassroom.service;

import com.solugenix.Virtualclassroom.entity.*;
import com.solugenix.Virtualclassroom.exceptions.NoDataFoundException;
import com.solugenix.Virtualclassroom.repo.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("adminService")
public class AdminServiceMgmtImpl implements IAdminServiceMgmt{


    @Autowired
    private AdminRepo  adminRepo;

    @Autowired
    private FacultySignupRepo facultySignupRepo;

    @Autowired
    IPptAndVideoEntityRepo  pptAndVideoEntityRepo;

    @Autowired
    private StudentSignupRepo studentSignupRepo;


//=========================================================================AdminListController- operations ================================

    @Override
    public String deleteAdminAccById(Long id) {
        Optional<AdminEntity> opt =adminRepo.findById(id);
        AdminEntity adminEntity ;
        if(opt.isPresent()){
            adminEntity = opt.get();
            adminRepo.deleteById(id);

            return "Admin acc Deleted Successfull . Account Details"+ "\n"+adminEntity;
        }
        else {
            return "No Admin Acc is Found To Delete having id :: "+ id;
        }
    }


    //=================================================================AdminRestContrller - operations ====================================
    @Override
    public List<AdminEntity> fetchAllAdmin() throws Exception {
        List<AdminEntity> adminList= adminRepo.findAll();
        System.out.println(adminList);
        if(adminList==null){
            System.out.println("no admin found");
            throw  new NoDataFoundException("No Admin Data Found");
        }
        return adminList;
    }


    @Override
    public List<FacultyEntity> fetchAllfaculty()throws Exception {
        List<FacultySignUpEntity>  facultySignUpList = facultySignupRepo.findAll();
        System.out.println(facultySignUpList);
        List<FacultyEntity>  facultyEntityList = new ArrayList<>();

        FacultyEntity fac = new FacultyEntity();

        if(facultySignUpList==null){
            throw  new NoDataFoundException("Faculty  Data Not Found");
        }else {
            for(FacultySignUpEntity factEntity : facultySignUpList){
                BeanUtils.copyProperties(factEntity, fac);
                facultyEntityList.add(fac);
            }
        }
        System.out.println(facultyEntityList);
        return facultyEntityList;
    }

    @Override
    public List<StudentEntity> fetchAllStudents() throws Exception{
        List<StudentSignupEntity> studentSignupEntityList = studentSignupRepo.findAll();
        System.out.println(studentSignupEntityList);
        List<StudentEntity> studentEntitiesList = new ArrayList<>();

        StudentEntity stud = new StudentEntity();
        if(studentSignupEntityList==null) {
            throw new NoDataFoundException("Student Data Not Found");
        }
        else {
            for(StudentSignupEntity studEntity : studentSignupEntityList){
                BeanUtils.copyProperties(studEntity, stud);
                studentEntitiesList.add(stud);
            }

        }
        System.out.println(studentEntitiesList);
        return studentEntitiesList;
    }

    @Override
    public List<PptAndVideoEntity> fetchAllPpts(String type) throws Exception{
        List<PptAndVideoEntity> pptList = pptAndVideoEntityRepo.findByTypeIs(type);
        if(pptList==null){
            throw new NoDataFoundException("Thare are no PPT's are Available !");
        }

        return pptList;
    }

    @Override
    public List<PptAndVideoEntity> fetchAllVideos(String type) throws Exception{
        List<PptAndVideoEntity> videoEntitiesList= pptAndVideoEntityRepo.findByTypeIs(type);
        if(videoEntitiesList==null){
            throw new NoDataFoundException("Thare are no Videos's are Available !");
        }
        return videoEntitiesList;
    }



    @Override
    public List<PptAndVideoEntity> fetchBothPptAndVideos() throws Exception {
        List<PptAndVideoEntity> pptList = pptAndVideoEntityRepo.findAll();
        if(pptList==null){
            throw new NoDataFoundException("Thare are no PPT's and Videos are Available !");
        }

        return pptList;
    }


    @Override
    public boolean checkAdminAuth(String username, String password) {
        AdminEntity   admin = adminRepo.findByUserNameAndPassword(username,password);
        if(admin!=null)
            return  true;
        return false;
    }

    @Override
    public AdminEntity createAdmin(AdminEntity admin) {
        AdminEntity adminEntity= adminRepo.save(admin);

        return adminEntity;
    }

    @Override
    public PptAndVideoEntity storePptOrVideo(PptAndVideoEntity pptOrVideo) {
        PptAndVideoEntity pptAndVideoEntity = pptAndVideoEntityRepo.save(pptOrVideo);
        return pptAndVideoEntity;
    }

    @Override
    public FacultySignUpEntity createFaculty(FacultySignUpEntity faculty) {
        FacultySignUpEntity facultySignUpEntity= facultySignupRepo.save(faculty);

        return facultySignUpEntity;
    }

//=========================================================================FacultyListController- operations ================================

    @Override
    public String deleteFacultyAccById(Long id) {
        Optional<FacultySignUpEntity> opt =facultySignupRepo.findById(id);
        FacultySignUpEntity facEntity ;

        if(opt.isPresent()){
            facEntity = opt.get();
            facultySignupRepo.deleteById(id);

            FacultyEntity fac = new FacultyEntity();
            BeanUtils.copyProperties(facEntity, fac);

             return "Faculty acc Deleted Successfull . Account Details"+ "\n"+fac;
        }
        else {
            return "No Faculty Acc is Found To Delete having id :: "+ id;
        }
    }


//=========================================================================PptAndVideoListController- operations ================================
    @Override
    public String deletePptORVideoById(Long id) {
          Optional<PptAndVideoEntity>  opt =pptAndVideoEntityRepo.findById(id);
          if(opt.isPresent()){
              PptAndVideoEntity  pve = opt.get();
              pptAndVideoEntityRepo.deleteById(id);
              return "Successfully Deleted . Having details :: \n"+pve;
          }
          else
              return "There nothing to Delete having id ::" +id;
    }

    @Override
    public PptAndVideoEntity getPptAndVideoById(int pid) throws NoDataFoundException {
          Optional<PptAndVideoEntity> opt =pptAndVideoEntityRepo.findById(pid);
          if(opt.isPresent()){
              return opt.get();
          }
          else
              throw new NoDataFoundException("NO PPT or Video available");


    }

    @Override
    public String updatePptOrVideo(PptAndVideoEntity pptOrVideo) {

        return pptAndVideoEntityRepo.save(pptOrVideo).getPId()+"Material Updated Succeffully";
    }

//=========================================================================StudentListController- operations ================================

    @Override
    public String deleteStudentAccById(Long id) {
        Optional<StudentSignupEntity> opt =studentSignupRepo.findById(id);
        StudentSignupEntity studEntity ;

        if(opt.isPresent()){
            studEntity = opt.get();
            studentSignupRepo.deleteById(id);

            StudentEntity stud = new StudentEntity();
            BeanUtils.copyProperties(studEntity, stud);

            return "Student acc Deleted Successfull . Account Details"+ "\n"+stud;
        }
        else {
            return "No Student Acc is Found To Delete having id :: "+ id;
        }
    }



}
