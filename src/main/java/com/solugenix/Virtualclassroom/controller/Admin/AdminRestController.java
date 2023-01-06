package com.solugenix.Virtualclassroom.controller.Admin;


import com.solugenix.Virtualclassroom.entity.*;
import com.solugenix.Virtualclassroom.exceptions.InvalidCreadiantials;
import com.solugenix.Virtualclassroom.exceptions.NoDataFoundException;
import com.solugenix.Virtualclassroom.exceptions.ResourceNotFoundException;
import com.solugenix.Virtualclassroom.exceptions.UsernameAlreadyExists;
import com.solugenix.Virtualclassroom.service.Admin.IAdminServiceMgmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminRestController {

    @Autowired
    private Environment env;

    @Autowired
    private IAdminServiceMgmt adminService;


    @GetMapping("/loginAsAdmin/{username}/{password}")
    public ResponseEntity<AdminEntity> showAdminLogin(@PathVariable String username, @PathVariable String password) throws InvalidCreadiantials {
               return ResponseEntity.ok().body(adminService.checkAdminAuth(username, password));
    }


    @PostMapping("/createAdmin")
    public ResponseEntity<?> AdminCreation( @RequestParam("action") String action,@RequestBody AdminEntity adminEntity) throws UsernameAlreadyExists {
        AdminEntity admin1 ;
        if(action.equals("updation") )     admin1= adminService.updateAdmin(adminEntity);

        else {
            adminService.userNameExits(adminEntity.getUsername());
            admin1=adminService.createAdmin(adminEntity);
        }

        return new ResponseEntity<AdminEntity>(admin1, HttpStatus.CREATED);
    }

    @PostMapping("/createFaculty")
    public ResponseEntity<?> showCreateFaculty( @RequestParam("action") String action,@RequestBody FacultySignUpEntity facultySignUpEntity) throws UsernameAlreadyExists {
        FacultySignUpEntity facultySignUpEntity1 ;
        if(action.equals("updation"))          facultySignUpEntity1= adminService.updateFaculty(facultySignUpEntity);
        else{
            adminService.userNameExits(facultySignUpEntity.getUsername());
            facultySignUpEntity1 = adminService.createFaculty(facultySignUpEntity);
        }

        return new ResponseEntity<>(facultySignUpEntity1, HttpStatus.OK);
    }


    @GetMapping("/showAllAdmin")
    public ResponseEntity<?> showAllAdmins() throws Exception {
        return new ResponseEntity<List<AdminEntity>>(adminService.fetchAllAdmin(), HttpStatus.OK);
    }

    @GetMapping("/showAllStudents")
    public ResponseEntity<?> showAllStudents() throws Exception {
        return new ResponseEntity<List<StudentEntity>>(adminService.fetchAllStudents(), HttpStatus.OK);
    }


    @GetMapping("/showAllFaculties")
    public ResponseEntity<?> showAllFaculties() throws Exception {
        return new ResponseEntity<List<FacultyEntity>>(adminService.fetchAllfaculty(), HttpStatus.OK);
    }




    @DeleteMapping("/deleteAccountsById")
    public boolean deleteAccById(@RequestParam("id") long id, @RequestParam("role") String role) throws ResourceNotFoundException, NoDataFoundException {
                 return adminService.deleteAccountByIdRole(id, role);
    }



    @PostMapping("/saveSubject")
    public SubjectEntity  saveSubject(@RequestBody SubjectEntity subjectEntity){
        return  adminService.addSubject(subjectEntity);
    }

    @GetMapping("/getAllSubjects")
    public List<SubjectEntity> getSubjects() throws ResourceNotFoundException{
         return  adminService.fetchAllSubjects();
    }

    @DeleteMapping("/deleteSubject/{id}")
    public boolean deleteSubject(@PathVariable("id") long id) throws ResourceNotFoundException, NoDataFoundException {
        return adminService.deleteSubject(id);
    }

}
