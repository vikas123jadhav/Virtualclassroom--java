package com.solugenix.Virtualclassroom.service.faculty;

import com.solugenix.Virtualclassroom.entity.FacultySignUpEntity;
import com.solugenix.Virtualclassroom.entity.StudentSignupEntity;
import com.solugenix.Virtualclassroom.exceptions.InvalidCreadiantials;

public interface IFacultMgmtService {

    public FacultySignUpEntity checkFacultyAuth(String userName , String password) throws InvalidCreadiantials;
}
