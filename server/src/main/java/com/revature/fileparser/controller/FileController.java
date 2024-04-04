package com.revature.fileparser.controller;

import com.revature.fileparser.entity.FileMetadata;
import com.revature.fileparser.entity.GenericRecord;
import com.revature.fileparser.entity.SimpleSpecification;
import com.revature.fileparser.service.FileService;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
public class FileController {

    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/files")
    public ResponseEntity<List<GenericRecord>> parseFiles( @RequestParam("specificationFile") MultipartFile specificationFile,
                                                           @RequestParam("recordFile") MultipartFile recordFile,
                                                           @RequestHeader("currentUser") String currentUser)
                                                 throws IOException {
        // Using a header as a workaround to get the username of the logged-in user from frontend
        if (currentUser.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        if (specificationFile.isEmpty() || recordFile.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        fileService.saveRecordFileMetadata(currentUser, recordFile);
        fileService.saveSimpleSpecification(currentUser, specificationFile);
        List<GenericRecord> recordList = fileService.saveRecordsFromFile(currentUser,
                recordFile.getOriginalFilename(), specificationFile.getOriginalFilename());
        return ResponseEntity.status(HttpStatus.OK).body(recordList);
    }

    // Get the flat-files of the user
    @GetMapping("users/{username}/flat-files")
    public ResponseEntity<List<FileMetadata>> getFilesByUsername(@PathVariable("username") String username){
        List<FileMetadata> filesOfUser = fileService.getFilesByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(filesOfUser);
    }

    // Get the spec-files of the user
    @GetMapping("users/{username}/specifications")
    public ResponseEntity<List<SimpleSpecification>> getSpecsByUsername(@PathVariable("username") String username){
        List<SimpleSpecification> specsOfUser = fileService.getSpecsByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(specsOfUser);
    }

}
