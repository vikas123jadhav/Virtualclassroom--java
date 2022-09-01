package com.solugenix.Virtualclassroom.controller;


import com.solugenix.Virtualclassroom.service.IAdminServiceMgmt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller("adminListController")
@RequestMapping("/adminList")
public class AdminListController {

    @Autowired
    private IAdminServiceMgmt  adminService;

    @GetMapping("/deleteAdminById")
    public String deleteAdminById(@RequestParam("aid") Long id, Map<String,Object> map){

        String result = adminService.deleteAdminAccById(id);
        map.put("resultInfo",result);
        return "adminList";
    }
}
