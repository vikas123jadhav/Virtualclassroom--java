package com.solugenix.Virtualclassroom.controller.faculty;


import com.solugenix.Virtualclassroom.entity.FacultySignUpEntity;
import com.solugenix.Virtualclassroom.service.faculty.IFacultMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/faculty")
@CrossOrigin(origins = "*")
public class FacultyLoginController {

    @Autowired
    private IFacultMgmtService facultMgmtService;


    @GetMapping("/loginAsFaculty/{username}/{password}")
    public ResponseEntity<FacultySignUpEntity> FacultyLogin(@PathVariable String username, @PathVariable String password) throws Exception {
        return ResponseEntity.ok().body( facultMgmtService.checkFacultyAuth(username, password));
    }


}
