package com.solugenix.Virtualclassroom.service.student;

import com.solugenix.Virtualclassroom.entity.FacultySignUpEntity;
import com.solugenix.Virtualclassroom.entity.StudentSignupEntity;
import com.solugenix.Virtualclassroom.exceptions.InvalidCreadiantials;
import com.solugenix.Virtualclassroom.exceptions.UsernameAlreadyExists;

public interface IStudentService {

    public StudentSignupEntity  checkStudentAuth(String username ,String password) throws InvalidCreadiantials;
    public  boolean checkUsernameAlreadyExists(String username) throws UsernameAlreadyExists;
    public StudentSignupEntity createStudent(StudentSignupEntity studentSignupEntity);

    public StudentSignupEntity updateStudent(StudentSignupEntity studentSignupEntity );
}
