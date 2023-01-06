package com.solugenix.Virtualclassroom.service.Admin;

import com.solugenix.Virtualclassroom.entity.*;
import com.solugenix.Virtualclassroom.exceptions.InvalidCreadiantials;
import com.solugenix.Virtualclassroom.exceptions.NoDataFoundException;
import com.solugenix.Virtualclassroom.exceptions.ResourceNotFoundException;
import com.solugenix.Virtualclassroom.exceptions.UsernameAlreadyExists;

import java.util.List;
import java.util.Set;

public interface IAdminServiceMgmt {

    public AdminEntity  checkAdminAuth(String username ,String password) throws InvalidCreadiantials;

    public  boolean checkUsernameAlreadyExists(String username) throws UsernameAlreadyExists;
    public  boolean checkUsernameAlreadyExistsFacutly(String username) throws UsernameAlreadyExists;
    public AdminEntity createAdmin(AdminEntity admin);



    public FacultySignUpEntity createFaculty(FacultySignUpEntity faculty);

    public List<AdminEntity> fetchAllAdmin()throws ResourceNotFoundException;
    public List<FacultyEntity> fetchAllfaculty()throws ResourceNotFoundException;
    public List<StudentEntity> fetchAllStudents()throws ResourceNotFoundException;

    public AdminEntity  updateAdmin(AdminEntity adminEntity );

    public FacultySignUpEntity  updateFaculty(FacultySignUpEntity facultySignUpEntity );



    public boolean  deleteAccountByIdRole(Long id , String role) throws ResourceNotFoundException;

    void userNameExits(String username) throws UsernameAlreadyExists;

   public List<SubjectEntity> fetchAllSubjects() throws ResourceNotFoundException;

   public  SubjectEntity addSubject(SubjectEntity subjectEntity);

   public boolean deleteSubject(Long id)throws ResourceNotFoundException;
}
