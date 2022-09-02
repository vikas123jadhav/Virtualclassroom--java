package com.solugenix.Virtualclassroom.controller;


import com.solugenix.Virtualclassroom.service.IAdminServiceMgmt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller("adminListController")
//@RequestMapping("/adminList")
public class AdminListController {

    @Autowired
    private IAdminServiceMgmt  adminService;

    @GetMapping("/deleteAdminById")
    public String deleteAdminById(@RequestParam("aid") Long id, RedirectAttributes attrs){

         if(adminService.deleteAdminAccById(id))
             attrs.addFlashAttribute("adminSucMsg","Admin Account Deleted Successfully");
         else
             attrs.addFlashAttribute("adminErrMsg","Failed to delete Admin Account");

        return "redirect:/showAllAdmin";
    }
}
