package com.solugenix.Virtualclassroom.controller;


import com.solugenix.Virtualclassroom.entity.*;
import com.solugenix.Virtualclassroom.model.PptAndVideoModel;
import com.solugenix.Virtualclassroom.service.IAdminServiceMgmt;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/admin")
public class AAdminRestController {

    @Autowired
    private Environment env;

    @Autowired
    private IAdminServiceMgmt   adminService;

    @GetMapping(value = {"/","/home","/classroom"})
    public String showHome(){

        return "home";
    }

    @GetMapping("/loginAsAdmin")
    public String showAdminLogin(@ModelAttribute ("admin") AdminEntity admin){

        return "AdminLogin";
    }


    @PostMapping("/loginAsAdmin")
    public String checkAdminAuthentication(@ModelAttribute ("admin") AdminEntity admin ,Map<String,Object> map){
//                                           @RequestParam String username , @RequestParam String password) {
           String username= admin.getUsername();
           String password = admin.getPassword();
        boolean result = adminService.checkAdminAuth(username,password);
        if(!result) {
            map.put("resultLogin", "Username or PassWord is Incorrect");
            return "AdminLogin";
        }
        else
            return "adminDashBoard";
    }

    @GetMapping("/toDashBoard")
    public String toAdminDashBoard(){
        return "adminDashBoard";
    }

     @GetMapping("/createAdmin")
     public String showAdminCreation(@ModelAttribute ("admin") AdminEntity admin){
        AdminEntity adminEntity = new AdminEntity();
         BeanUtils.copyProperties(adminEntity,admin);
        return "adminCreation";
     }

    @PostMapping("/createAdmin")
    public  String  storeAdmin(@ModelAttribute ("admin") AdminEntity admin ,Map<String,Object> map){
        if(admin.getUsername()==null){
            map.put("usernameResult" ,"*UserName can't be Empty");
            return  "adminCreation";
        }
        else if(admin.getPassword()==null){
            map.put("pwdCreation" ,"*Password Required");
            return  "adminCreation";
        }

        AdminEntity admin1= adminService.createAdmin(admin);
        if(admin1==null){
            map.put("failCreation" ,"Unable to create Admin Account");
            return  "adminCreation";
        }

        else {
            map.put("successCreation","SuccessFully Admin Account Created having , Username :: "+ admin1.getUsername()+"; Password :: " +admin1.getPassword());
            return "adminCreation";
        }
    }

    @GetMapping("/showAllAdmin")
    public String showAllAdmins( Map<String,Object> map) throws Exception{
        List<AdminEntity> adminList = adminService.fetchAllAdmin();
        map.put("adminList",adminList);
        return "adminList";
    }


    @GetMapping("/showAllStudents")
    public String showAllStudents(Map<String,Object> map) throws Exception{
        List<StudentEntity> studentList = adminService.fetchAllStudents();
//        System.out.println(studentList);
        map.put("studentList",studentList);
        return "studentList";
    }

    @GetMapping("/storePpt")
    public String showIsertPpt (@ModelAttribute("pptAndVideoModel") PptAndVideoModel pptAndVideoModel,Map<String,Object> map){

        return "pptStore";

    }





    //@PostMapping(value = {"/storePpt","/storeVideo"})
    @PostMapping("/storePpt")
    public String insertPptOrVideo(@ModelAttribute("pptAndVideoModel") PptAndVideoModel pptAndVideoModel, Map<String,Object> map) throws IOException {
          // get folder location to store uploaded files
        String location = env.getProperty("upload.storePPT");
        File locationStore = new File(location);

        // create location folder if it is not already avilable
        if(!locationStore.exists())
            locationStore.mkdir();

        // get the original names of the uploading files
        MultipartFile  pptFile = pptAndVideoModel.getPath();

        String pptFileName = pptFile.getOriginalFilename();

        // create InputStreams representing the Uploading files
        InputStream pptIStream = pptFile.getInputStream();

        // create OutPutStreams pointing to destionation file on server machine file
        OutputStream  pptOStream = new FileOutputStream(location+"/"+ pptFileName);

        // complete operation among the streams
        IOUtils.copy(pptIStream,pptOStream);

        // close streams
        pptIStream.close();
        pptOStream.close();

        // create Entity class Object
        PptAndVideoEntity  ppt = new PptAndVideoEntity();
        ppt.setSubject( pptAndVideoModel.getSubject());
        ppt.setType(pptAndVideoModel.getType());
        ppt.setPath(location+"/"+pptFileName);

        // use Service
         String msg ="PPT is Stored having ID is :: "+adminService.storePptOrVideo(ppt).getPId();

         String fileInfo="Uploaded PPT is :: "+pptFileName;

         // create model attribute
        map.put("pptFile", fileInfo);
        map.put("resultMsg",msg);


        return "pptStore";
    }

    @GetMapping("/storeVideo")
    public String showIsertVideo(@ModelAttribute("pptAndVideoModel") PptAndVideoModel pptAndVideoModel,Map<String,Object> map){
        return "videoStore";
    }

    @PostMapping("/storeVideo")
    public String insertVideo(@ModelAttribute("pptAndVideoModel") PptAndVideoModel pptAndVideoModel,Map<String,Object> map) throws IOException {

        // get folder location to store uploaded files
        String location = env.getProperty("upload.storeVIDEO");
        File locationStore = new File(location);

        // create location folder if it is not already avilable
        if(!locationStore.exists())
            locationStore.mkdir();

        // get the original names of the uploading files
        MultipartFile  videoFile = pptAndVideoModel.getPath();

        String videoFileName = videoFile.getOriginalFilename();

        // create InputStreams representing the Uploading files
        InputStream videoIStream = videoFile.getInputStream();

        // create OutPutStreams pointing to destionation file on server machine file
        OutputStream  videoOStream = new FileOutputStream(location+"/"+ videoFileName);

        // complete operation among the streams
        IOUtils.copy(videoIStream,videoOStream);

        // close streams
        videoIStream.close();
        videoOStream.close();

        // create Entity class Object
        PptAndVideoEntity  ppt = new PptAndVideoEntity();
        ppt.setSubject( pptAndVideoModel.getSubject());
        ppt.setType(pptAndVideoModel.getType());
        ppt.setPath(location+"/"+videoFileName);

        // use Service
        String msg ="PPT is Stored having ID is :: "+adminService.storePptOrVideo(ppt).getPId();

        String fileInfo="Uploaded PPT is :: "+videoFileName;
        // create model attribute
        map.put("videoFile", fileInfo);
        map.put("resultMsg",msg);

         return "videoStore";
    }


    @GetMapping("/showAllFaculties")
    public String showAllFaculties(Map<String,Object> map) throws Exception{
        System.out.println("showAll facultiy method executed");
        List<FacultyEntity> facultyLists = adminService.fetchAllfaculty();

//        System.out.println(facultyLists);
        map.put("facultyLists",facultyLists);
        return "facultyList";
    }


    @GetMapping("/showAllPpts")
    public  String showAllPpts(Map<String,Object> map) throws Exception{
        List<PptAndVideoEntity> pptList = adminService.fetchAllPpts("ppt");
        map.put("pptLists",pptList);
        return "pptList";
    }

    @GetMapping("/showAllVideos")
    public  String showAllVideos(Map<String,Object> map) throws Exception{
        List<PptAndVideoEntity> videoList = adminService.fetchAllVideos("video");
        map.put("videoLists",videoList);
        return "videoList";
    }

    @GetMapping("/showPptAndVideo")
    public  String showAllPptAndVideos(Map<String,Object> map) throws Exception{
        List<PptAndVideoEntity> pptAndVideoEntityList = adminService.fetchBothPptAndVideos();
        map.put("pptAndvideoLists" , pptAndVideoEntityList);
        return "pptAndvideoList";
    }



    @GetMapping("/createFaculty")
    public  String showCreateFaculty(@ModelAttribute("faculty") FacultySignUpEntity faculty){
        FacultySignUpEntity fac = new FacultySignUpEntity();
        BeanUtils.copyProperties(fac,faculty);
        return "facultyCreation";
    }

    @PostMapping("/createFaculty")
    public String storeFaculty(@ModelAttribute("faculty") FacultySignUpEntity faculty , Map<String,Object> map){

        FacultySignUpEntity facultyEntity = adminService.createFaculty(faculty);
        if(facultyEntity==null){
            map.put("resultInfo","Unable to Create Faculty Account");
            return "facultyCreation";
        }
        else {
            map.put("msgInfo","Succuffully Created having , User-name::" + facultyEntity.getUserName()+" , Passsword ::"+ facultyEntity.getPassword());
            return "facultyCreation";
        }

    }


}
