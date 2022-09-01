package com.solugenix.Virtualclassroom.controller;

import com.solugenix.Virtualclassroom.service.IAdminServiceMgmt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("facultyListController")
@RequestMapping("facultyList")
public class FacultyListController {

    @Autowired
    private IAdminServiceMgmt adminService;

    @GetMapping("/deleteFacultyById")
    public String deleteAdminById(@RequestParam Long fId){
        String result = adminService.deleteFacultyAccById(fId);
        return result;
    }
}


