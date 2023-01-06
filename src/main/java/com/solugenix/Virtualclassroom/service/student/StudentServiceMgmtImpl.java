package com.solugenix.Virtualclassroom.service.student;

import com.solugenix.Virtualclassroom.entity.CredentialsEntity;
import com.solugenix.Virtualclassroom.entity.StudentSignupEntity;
import com.solugenix.Virtualclassroom.exceptions.InvalidCreadiantials;
import com.solugenix.Virtualclassroom.exceptions.UsernameAlreadyExists;
import com.solugenix.Virtualclassroom.model.CredentialsStoring;
import com.solugenix.Virtualclassroom.repo.CredentialsEntityRepo;
import com.solugenix.Virtualclassroom.repo.StudentSignupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentServiceMgmtImpl implements IStudentService {

    @Autowired
    private StudentSignupRepo studentSignupRepo;

    @Autowired
    private CredentialsEntityRepo credentialsEntityRepo;



    @Override
    public StudentSignupEntity checkStudentAuth(String username, String password) throws InvalidCreadiantials {
        StudentSignupEntity student = studentSignupRepo.findByUserNameAndPassword(username, password);
        if (student == null)
            throw new InvalidCreadiantials("Invalid Student crediantials");
        return student;
    }

    @Override
    public boolean checkUsernameAlreadyExists(String username) throws UsernameAlreadyExists {
        StudentSignupEntity student = studentSignupRepo.findByUsernameEquals(username);
        if (student != null)
            throw new UsernameAlreadyExists("Student UserName is already Existing, Try different 'user name'");
        return true;
    }

    @Override
    public StudentSignupEntity createStudent(StudentSignupEntity studentSignupEntity) {
        StudentSignupEntity student = studentSignupRepo.save(studentSignupEntity);
        credentialsEntityRepo.save(CredentialsStoring.copyData(student));
        return student;
    }

    @Override
    public StudentSignupEntity updateStudent(StudentSignupEntity studentSignupEntity) {
        CredentialsEntity  credentialsEntity = credentialsEntityRepo.findByAccId(studentSignupEntity.getId());

        CredentialsEntity credentialsEntity1=CredentialsStoring.copyData(studentSignupEntity);
        credentialsEntity1.setCredentialsId( credentialsEntity.getCredentialsId());
        StudentSignupEntity studentSignupEntity1= studentSignupRepo.save(studentSignupEntity);

        credentialsEntityRepo.save(credentialsEntity1);
        return studentSignupEntity1;
    }


}
