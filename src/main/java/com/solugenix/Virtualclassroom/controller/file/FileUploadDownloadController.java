package com.solugenix.Virtualclassroom.controller.file;


import com.solugenix.Virtualclassroom.entity.DatabaseFileEnity;
import com.solugenix.Virtualclassroom.exceptions.ResourceNotFoundException;
import com.solugenix.Virtualclassroom.payload.Response;
import com.solugenix.Virtualclassroom.repo.DatabaseFileRepository;
import com.solugenix.Virtualclassroom.service.file.DatabaseFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Paths.get;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "*")
public class FileUploadDownloadController {

    @Autowired
    private DatabaseFileService fileStorageService;


    @Autowired
    private DatabaseFileRepository dbFileRepository;

    // define a location
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";


    @GetMapping("/getAllFiles/{type}")
    public List<DatabaseFileEnity> getAllFiles(@PathVariable("type") String type) throws ResourceNotFoundException {
        return  fileStorageService.fetchAllFiles(type);
    }




    @PostMapping("/uploadFile/{subject}/{type}/{uploadedId}/{uploadedByUsername}/{uploadedRole}")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @PathVariable("subject") String subject,
                                             @PathVariable("type") String type ,
                                             @PathVariable("uploadedId") String uploadedId ,
                                             @PathVariable("uploadedByUsername") String uploadedByUsername ,
                                             @PathVariable("uploadedRole") String uploadedRole) throws IOException {


        System.out.println(file+"\n"+subject+"\n"+type);
//        DatabaseFileEnity fileName = fileStorageService.storeFile(file,subject,type);
        DatabaseFileEnity fileName = fileStorageService.storeFile(file,subject,type,uploadedId,uploadedByUsername,uploadedRole);
        return  ResponseEntity.ok().body(StringUtils.cleanPath(file.getOriginalFilename()));
    }

    @GetMapping("/getFilesByUploader/{uploadedId}")
    public List<DatabaseFileEnity> getByFileUploader(@PathVariable("uploadedId") String uploadedId){
        return  fileStorageService.fetchFilesByUploader(uploadedId);
    }

    @PostMapping("/uploadUpdateFile/{subject}/{type}/{pvid}/{uploadedId}/{uploadedByUsername}/{uploadedRole}")
    public Response updateFile(@RequestParam("file") MultipartFile file,
                               @PathVariable("subject") String subject,
                               @PathVariable("type") String type ,
                               @PathVariable("pvid") Long pvid ,
                               @PathVariable("uploadedId") String uploadedId ,
                               @PathVariable("uploadedByUsername") String uploadedByUsername ,
                               @PathVariable("uploadedRole") String uploadedRole) throws IOException {
        System.out.println(file+"\n"+subject+"\n"+type+"\n"+pvid);

        DatabaseFileEnity fileName = fileStorageService.updateFile(file,subject,type,pvid,uploadedId,uploadedByUsername,uploadedRole);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName.getFileName())
                .toUriString();

        return new Response(subject,type,fileName.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }


    @GetMapping("/getFileById/{pvid}")
    public DatabaseFileEnity  getFileById(@PathVariable("pvid") Long pvid) throws ResourceNotFoundException {
            return fileStorageService.getFile(pvid);
    }


    @DeleteMapping("/deletePptORVideoById")
    public boolean  deleteFileById(@RequestParam("pvid") Long pvid) throws Exception {
        if(fileStorageService.deletePptORVideoById(pvid)!=null)
            return true;
        return false;
    }

//    @PostMapping("/uploadMultipleFiles")
//    public List<Response> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toList());
//    }

    @GetMapping("/downloadFile/{fileId:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable long fileId, HttpServletRequest request) throws ResourceNotFoundException {
        // Load file as Resource
        DatabaseFileEnity databaseFileEnity = fileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(databaseFileEnity.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFileEnity.getFileName() + "\"")
                .body(new ByteArrayResource(databaseFileEnity.getData()));
    }


    @GetMapping("download/{filename}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
        Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
        if(!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " was not found on the server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);

    }

    @GetMapping("/getRecordChart/{type}")
    public  ResponseEntity<?> getRecordsCount(@PathVariable("type") String type){
      //List<PptVideoCount> count= dbFileRepository.countTotalDatabaseFilesByMonthClass();

        System.out.println( dbFileRepository.getCountByMonth(type));
        System.out.println( dbFileRepository.getCountByRecords(type));

        return   new ResponseEntity<>(null,HttpStatus.OK);
    }
}