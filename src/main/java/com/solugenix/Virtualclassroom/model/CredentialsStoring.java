package com.solugenix.Virtualclassroom.model;

import com.solugenix.Virtualclassroom.entity.AdminEntity;
import com.solugenix.Virtualclassroom.entity.CredentialsEntity;
import com.solugenix.Virtualclassroom.entity.FacultySignUpEntity;
import com.solugenix.Virtualclassroom.entity.StudentSignupEntity;
import org.springframework.stereotype.Component;


public class CredentialsStoring {

    public static CredentialsEntity  copyData(Object obj){
        CredentialsEntity credentials=new CredentialsEntity();
          if(obj instanceof AdminEntity){
               AdminEntity admin=(AdminEntity) obj;
               credentials.setUsername(admin.getUsername());
               credentials.setPassword(admin.getPassword());
               credentials.setRoleid(admin.getROLE_ID());
               credentials.setRole(admin.getRole());
               credentials.setStatus(admin.getStatus());
               credentials.setAccId(admin.getId());
               credentials.setName(admin.getName());

               return  credentials;
          }

          else if(obj instanceof FacultySignUpEntity){
              FacultySignUpEntity faculty = (FacultySignUpEntity) obj;
              credentials.setUsername(faculty.getUsername());
              credentials.setPassword(faculty.getPassword());
              credentials.setRoleid(faculty.getRoleid());
              credentials.setRole(faculty.getRole());
              credentials.setStatus(faculty.getStatus());
              credentials.setAccId(faculty.getId());
              credentials.setName(faculty.getName());
              return  credentials;
          }
         else if(obj instanceof StudentSignupEntity){
             StudentSignupEntity student = (StudentSignupEntity) obj;
              credentials.setUsername(student.getUsername());
              credentials.setPassword(student.getPassword());
              credentials.setRoleid(student.getRoleid());
              credentials.setRole(student.getRole());
              credentials.setStatus(student.getStatus());
              credentials.setAccId(student.getId());
              credentials.setName(student.getName());

              return  credentials;
          }

         return credentials;
    }
}
