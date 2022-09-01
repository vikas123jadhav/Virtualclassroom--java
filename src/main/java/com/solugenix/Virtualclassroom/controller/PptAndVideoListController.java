package com.solugenix.Virtualclassroom.controller;

import com.solugenix.Virtualclassroom.entity.PptAndVideoEntity;
import com.solugenix.Virtualclassroom.exceptions.NoDataFoundException;
import com.solugenix.Virtualclassroom.service.IAdminServiceMgmt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller("pptAndVideoListController")
@RequestMapping("pptAndVideoList")
public class PptAndVideoListController {
    @Autowired
    private IAdminServiceMgmt adminService;

    @GetMapping("/editPptOrVideo")
    public String showEditPptorVideo(@ModelAttribute PptAndVideoEntity pptAndVideoEntity , @RequestParam int pid) throws NoDataFoundException {
        PptAndVideoEntity pptAndVideoEntity1 = adminService.getPptAndVideoById(pid);
        BeanUtils.copyProperties(pptAndVideoEntity1,pptAndVideoEntity);
        return "edit_pptOrVideo";
    }

    @PostMapping("/editPptOrVideo")
    public String editPptOrVideo(@ModelAttribute PptAndVideoEntity pptAndVideoEntity, Map<String,Object> map){
        String result = adminService.updatePptOrVideo(pptAndVideoEntity);
        map.put("resultInfo",result);
        return "pptList";
    }

    @GetMapping("/deletePptOrVideoById")
    public String deleteAdminById(@RequestParam Long id){
        String result = adminService.deletePptORVideoById(id);
        return result;
    }
}
