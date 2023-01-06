package com.solugenix.Virtualclassroom.service.credential;


import com.solugenix.Virtualclassroom.entity.CredentialsEntity;
import com.solugenix.Virtualclassroom.repo.AdminRepo;
import com.solugenix.Virtualclassroom.repo.CredentialsEntityRepo;
import com.solugenix.Virtualclassroom.repo.FacultySignupRepo;
import com.solugenix.Virtualclassroom.repo.StudentSignupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CredentialService implements  UserDetailsService {

    @Autowired
    private CredentialsEntityRepo credentialsEntityRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private FacultySignupRepo facultySignupRepo;

    @Autowired
    private StudentSignupRepo studentSignupRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CredentialsEntity credentials = credentialsEntityRepo.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(credentials.getUsername(),credentials.getPassword(),new ArrayList<>());
    }


    public Object loadUserByUsernameForCredentials(String username)  {
        CredentialsEntity credentials = credentialsEntityRepo.findByUsername(username);
        if(credentials.getRoleid()==101)              return adminRepo.findById(credentials.getAccId());
        else if(credentials.getRoleid()==202)         return facultySignupRepo.findById(credentials.getAccId());
        else if(credentials.getRoleid()==303)         return studentSignupRepo.findById(credentials.getAccId());
        else                                          return  new Exception("No Account found to load by username");

   }
}
