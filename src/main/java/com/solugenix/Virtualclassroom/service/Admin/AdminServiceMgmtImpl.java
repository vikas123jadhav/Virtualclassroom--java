package com.solugenix.Virtualclassroom.service.Admin;

import com.solugenix.Virtualclassroom.entity.*;
import com.solugenix.Virtualclassroom.exceptions.InvalidCreadiantials;
import com.solugenix.Virtualclassroom.exceptions.ResourceNotFoundException;
import com.solugenix.Virtualclassroom.exceptions.UsernameAlreadyExists;
import com.solugenix.Virtualclassroom.model.CredentialsStoring;
import com.solugenix.Virtualclassroom.repo.*;
import com.solugenix.Virtualclassroom.service.student.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("adminService")
public class AdminServiceMgmtImpl implements IAdminServiceMgmt {

    @Autowired
    private AdminRepo  adminRepo;

    @Autowired
    private FacultySignupRepo facultySignupRepo;

    @Autowired
    private StudentSignupRepo studentSignupRepo;


    @Autowired
    private IStudentService  studentService;

    @Autowired
    private CredentialsEntityRepo credentialsEntityRepo;

    @Autowired
    private  SubjectRepo subjectRepo;

    @Override
    public List<AdminEntity> fetchAllAdmin() throws ResourceNotFoundException {
        List<AdminEntity> adminList= adminRepo.findAll();
        System.out.println("getting data from db"+adminList);
        if(adminList==null)
            throw  new ResourceNotFoundException("No Admin Data Found");
        return adminList;
    }


    @Override
    public List<FacultyEntity> fetchAllfaculty()throws  ResourceNotFoundException {
        List<FacultySignUpEntity>  facultySignUpList = facultySignupRepo.findAll();
        List<FacultyEntity>  facultyEntityList = new ArrayList<>();

        if(facultySignUpList==null){
            throw  new ResourceNotFoundException("Faculty  Data Not Found");
        }else {
            for(FacultySignUpEntity factEntity : facultySignUpList){
                FacultyEntity fac = new FacultyEntity();
                BeanUtils.copyProperties(factEntity, fac);
                facultyEntityList.add(fac);
            }
        }
        return facultyEntityList;
    }

    @Override
    public List<StudentEntity> fetchAllStudents() throws  ResourceNotFoundException{
        List<StudentSignupEntity> studentSignupEntityList = studentSignupRepo.findAll();
        List<StudentEntity> studentEntitiesList = new ArrayList<>();
        if(studentSignupEntityList==null) {
            throw new ResourceNotFoundException("Student Data Not Found");
        }
        else {
            for(StudentSignupEntity studEntity : studentSignupEntityList){
                StudentEntity stud = new StudentEntity();
                BeanUtils.copyProperties(studEntity, stud);
                studentEntitiesList.add(stud);
            }
        }
        return studentEntitiesList;
    }






    @Override
    public AdminEntity checkAdminAuth(String username, String password) throws InvalidCreadiantials {
        AdminEntity   admin = adminRepo.findByUserNameAndPassword(username,password);
        if(admin==null)
            throw new InvalidCreadiantials("Invalid Admin creadiantials ");
        return admin;
    }

    public void userNameExits(String username) throws UsernameAlreadyExists{
        checkUsernameAlreadyExists(username);
        checkUsernameAlreadyExistsFacutly(username);
        studentService.checkUsernameAlreadyExists(username);
    }
    @Override
    public  boolean checkUsernameAlreadyExists(String username) throws UsernameAlreadyExists {
             AdminEntity admin =adminRepo.findByUsernameEquals(username);
             if(admin!=null)
                 throw new UsernameAlreadyExists("Adim UserName is already Existing, Try different 'user name'");
             return  true;
    }

    @Override
    public  boolean checkUsernameAlreadyExistsFacutly(String userName) throws UsernameAlreadyExists {
        FacultySignUpEntity faculty =facultySignupRepo.findByUsernameEquals(userName);
        if(faculty!=null)
            throw new UsernameAlreadyExists("Faculty UserName is already Existing, Try different 'user name'");
        return  true;
    }
    @Override
    public AdminEntity createAdmin(AdminEntity admin) {
        AdminEntity adminEntity= adminRepo.save(admin);
        credentialsEntityRepo.save(  CredentialsStoring.copyData(adminEntity));
        return adminEntity;
    }

    @Override
    public AdminEntity updateAdmin(AdminEntity adminEntity ) {
        CredentialsEntity  credentialsEntity = credentialsEntityRepo.findByAccId(adminEntity.getId());

        CredentialsEntity credentialsEntity1=CredentialsStoring.copyData(adminEntity);
        credentialsEntity1.setCredentialsId( credentialsEntity.getCredentialsId());
        AdminEntity adminEntity1= adminRepo.save(adminEntity);

        credentialsEntityRepo.save(credentialsEntity1);
        return adminEntity1;
    }

    @Override
    public FacultySignUpEntity updateFaculty(FacultySignUpEntity facultySignUpEntity) {
        CredentialsEntity  credentialsEntity = credentialsEntityRepo.findByAccId(facultySignUpEntity.getId());

        CredentialsEntity credentialsEntity1=CredentialsStoring.copyData(facultySignUpEntity);
        credentialsEntity1.setCredentialsId( credentialsEntity.getCredentialsId());
        FacultySignUpEntity facultySignUp1= facultySignupRepo.save(facultySignUpEntity);

        credentialsEntityRepo.save(credentialsEntity1);
        return  facultySignUp1;
    }



    @Override
    public FacultySignUpEntity createFaculty(FacultySignUpEntity faculty) {
        FacultySignUpEntity facultySignUpEntity= facultySignupRepo.save(faculty);
        credentialsEntityRepo.save(CredentialsStoring.copyData(facultySignUpEntity));
        return facultySignUpEntity;
    }




    @Override
    public boolean deleteAccountByIdRole(Long id, String role) throws ResourceNotFoundException {
        if(role.equals("admin")){
            AdminEntity admin =
                    adminRepo.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Admin not found to delete on :: " + id));
            adminRepo.delete(admin);
            deleteAccCredentials(admin.getId());
            if(admin!=null) return true;
            else return false;
        }
        else if(role.equals("faculty")){

            FacultySignUpEntity  faculty =
                    facultySignupRepo.findById(id)
                            .orElseThrow(()->new ResourceNotFoundException("Faculty not found to delete on :: " + id));
            facultySignupRepo.delete(faculty);
            deleteAccCredentials(faculty.getId());
            if(faculty!=null) return true;
            else return false;
        }
        else {
            StudentSignupEntity student =
                    studentSignupRepo.findById(id)
                            .orElseThrow(()->new ResourceNotFoundException("Student not found to delete on :: " + id));
            studentSignupRepo.delete(student);
            if(student!=null) return true;
            else  return false;
        }
    }

     public void deleteAccCredentials(Long id){
        credentialsEntityRepo.deleteByAccId(id);
     }


     @Override
     public List<SubjectEntity> fetchAllSubjects() throws ResourceNotFoundException {
        return  subjectRepo.findAll();
     }

    @Override
    public SubjectEntity addSubject(SubjectEntity subjectEntity) {
        return  subjectRepo.save(subjectEntity);
    }

    @Override
    public boolean deleteSubject(Long id) throws  ResourceNotFoundException{
        SubjectEntity subject =
                subjectRepo.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Student not found to delete on :: " + id));
        subjectRepo.delete(subject);
        if(subject!=null) return true;
        else  return false;
    }


}
