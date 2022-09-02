package com.solugenix.Virtualclassroom.controller;

import com.solugenix.Virtualclassroom.service.IAdminServiceMgmt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("facultyListController")
//@RequestMapping("facultyList")
public class FacultyListController {

    @Autowired
    private IAdminServiceMgmt adminService;

    @GetMapping("/deleteFacultyById")
    public String deleteAdminById(@RequestParam("fid") Long fid , RedirectAttributes attrs){

        if(adminService.deleteFacultyAccById(fid))
            attrs.addFlashAttribute("facSucMsg","Faculty Account Deleted Successfully");
        else
            attrs.addFlashAttribute("facErrMsg","Failed to delete Faculty Account");

        return "redirect:/showAllFaculties";
    }
}


