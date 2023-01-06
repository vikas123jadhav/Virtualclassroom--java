package com.solugenix.Virtualclassroom.service.file;


import com.solugenix.Virtualclassroom.exceptions.FileStorageException;
import com.solugenix.Virtualclassroom.exceptions.ResourceNotFoundException;
import com.solugenix.Virtualclassroom.entity.DatabaseFileEnity;
import com.solugenix.Virtualclassroom.repo.DatabaseFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class DatabaseFileService {

    @Autowired
    private DatabaseFileRepository dbFileRepository;

    // define a location
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";

    public List<DatabaseFileEnity> fetchFilesByUploader(String uploadedId){
        return dbFileRepository.findByUploadedId(uploadedId);
    }

    public List<DatabaseFileEnity> fetchAllFiles(String type) throws ResourceNotFoundException {

        List<DatabaseFileEnity> databaseFileEnity =null;
        if(type.equalsIgnoreCase("ppt") || type.equalsIgnoreCase("video"))
            databaseFileEnity = dbFileRepository.findByTypeIs(type);
        else if(type.equals("bothTypes"))
            databaseFileEnity = dbFileRepository.findAll();
        else
            throw new IllegalArgumentException("Invalid Document Type");
        if(databaseFileEnity ==null)
            throw new ResourceNotFoundException("Thare are no PPT's are Available !");
        return databaseFileEnity;
    }


    public DatabaseFileEnity deletePptORVideoById(Long id) throws ResourceNotFoundException  {
        DatabaseFileEnity databaseFileEnity =
                dbFileRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Resource not found to delete on :: " + id));
        dbFileRepository.delete(databaseFileEnity);
        return databaseFileEnity;
    }

    public DatabaseFileEnity save(DatabaseFileEnity dbE){
        return  dbFileRepository.save(dbE);
    }



    public DatabaseFileEnity storeFile(MultipartFile multipartFile, String subject, String type
                                   ,String uploadedId ,String uploadedByUsername,String uploadedRole) throws IOException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Path fileStorage = get(DIRECTORY, fileName).toAbsolutePath().normalize();
        copy(multipartFile.getInputStream(), fileStorage, REPLACE_EXISTING);


        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DatabaseFileEnity dbFile = new DatabaseFileEnity(fileName, multipartFile.getContentType(),subject,type,uploadedId,uploadedByUsername,uploadedRole);

            return dbFileRepository.save(dbFile);
        } catch (Exception ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

//    public DatabaseFileEnity storeFile(MultipartFile multipartFile, String subject, String type) throws IOException {
//        // Normalize file name
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        Path fileStorage = get(DIRECTORY, fileName).toAbsolutePath().normalize();
//        copy(multipartFile.getInputStream(), fileStorage, REPLACE_EXISTING);
//
//
//        try {
//            // Check if the file's name contains invalid characters
//            if(fileName.contains("..")) {
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//            }
//
//            DatabaseFileEnity dbFile = new DatabaseFileEnity(fileName, multipartFile.getContentType(),subject,type);
//
//            return dbFileRepository.save(dbFile);
//        } catch (Exception ex) {
//            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//        }
//    }

    public DatabaseFileEnity updateFile(MultipartFile multipartFile, String subject, String type , Long pvid
                                       ,String uploadedId ,String uploadedByUsername,String uploadedRole) throws IOException {

        // Normalize file name
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Path fileStorage = get(DIRECTORY, fileName).toAbsolutePath().normalize();
        copy(multipartFile.getInputStream(), fileStorage, REPLACE_EXISTING);

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DatabaseFileEnity dbFile = new DatabaseFileEnity(fileName, multipartFile.getContentType(), null,subject,type,pvid ,uploadedId,uploadedByUsername,uploadedRole);

            return dbFileRepository.save(dbFile);
        } catch (Exception ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DatabaseFileEnity getFile(long fileId) throws ResourceNotFoundException {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("File not found with id " + fileId));
    }
}
