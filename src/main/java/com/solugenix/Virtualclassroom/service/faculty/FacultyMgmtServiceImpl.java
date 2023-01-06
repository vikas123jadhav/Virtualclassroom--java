package com.solugenix.Virtualclassroom.service.faculty;

import com.solugenix.Virtualclassroom.entity.AdminEntity;
import com.solugenix.Virtualclassroom.entity.FacultySignUpEntity;
import com.solugenix.Virtualclassroom.entity.StudentSignupEntity;
import com.solugenix.Virtualclassroom.exceptions.InvalidCreadiantials;
import com.solugenix.Virtualclassroom.repo.FacultySignupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Service("facultyService")
public class FacultyMgmtServiceImpl implements IFacultMgmtService  , UserDetailsService {


    @Autowired
    private  FacultySignupRepo facultySignupRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FacultySignUpEntity facultySignUpEntity = facultySignupRepo.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(facultySignUpEntity.getUsername(),facultySignUpEntity.getPassword(),new ArrayList<>());

    }

    @Override
    public FacultySignUpEntity checkFacultyAuth(String userName, String password) throws InvalidCreadiantials {
        FacultySignUpEntity faculty=    facultySignupRepo.findByUsernameAndPassword(userName,password);
        if(faculty==null)
            throw new InvalidCreadiantials("Invalid Faculty creadiantials ");
        return faculty;
    }


}
