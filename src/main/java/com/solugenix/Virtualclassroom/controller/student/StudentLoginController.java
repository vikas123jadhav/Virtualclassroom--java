package com.solugenix.Virtualclassroom.controller.student;


import com.solugenix.Virtualclassroom.entity.StudentSignupEntity;

import com.solugenix.Virtualclassroom.service.Admin.IAdminServiceMgmt;
import com.solugenix.Virtualclassroom.service.student.IStudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
public class StudentLoginController {

    @Autowired
    private IStudentService studentService;
    @Autowired
    private IAdminServiceMgmt adminServiceMgmt;

   @GetMapping("/loginAsStudent/{username}/{password}")
    public ResponseEntity<StudentSignupEntity>  checkStudentAuthLogin(@PathVariable String username , @PathVariable String password) throws Exception{
            return ResponseEntity.ok().body(studentService.checkStudentAuth(username,password));
      }



    @PostMapping("/createStudent")
    public  ResponseEntity<?>  StudentSignUp( @RequestParam("action") String action,@RequestBody StudentSignupEntity studentSignup) throws Exception{
        StudentSignupEntity studentSignupEntity1 ;
        if(action.equals("updation") ){
            studentSignupEntity1= studentService.updateStudent(studentSignup);
        }
        else {
            adminServiceMgmt.userNameExits(studentSignup.getUsername());
            studentSignupEntity1 = studentService.createStudent(studentSignup);
        }
        return  new ResponseEntity<StudentSignupEntity>(studentSignupEntity1,HttpStatus.CREATED);
    }


}
