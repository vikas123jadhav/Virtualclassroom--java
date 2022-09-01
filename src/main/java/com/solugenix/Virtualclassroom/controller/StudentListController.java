package com.solugenix.Virtualclassroom.controller;

import com.solugenix.Virtualclassroom.service.IAdminServiceMgmt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("studentListController")
@RequestMapping("/studentList")
public class StudentListController {

    @Autowired
    private IAdminServiceMgmt adminService;

    @GetMapping("/deleteStudentById")
    public String deleteAdminById(@RequestParam Long sid){
        String result = adminService.deleteStudentAccById(sid);
        return result;
    }
}
