package com.revature.fileparser.controller;

import java.util.List;
import com.revature.fileparser.entity.GenericRecord;
import com.revature.fileparser.service.GenericRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class GenericRecordController {

    private GenericRecordService genericRecordService;

    @Autowired
    public GenericRecordController(GenericRecordService genericRecordService) {
        this.genericRecordService = genericRecordService;
    }

    // Get all the records of a user
    @GetMapping("users/{username}/records")
    public ResponseEntity<List<GenericRecord>> getRecordsByUsername(@PathVariable("username") String username){
        List<GenericRecord> recordsOfUser = genericRecordService.getRecordsByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(recordsOfUser);
    }


    // Get all records of the user of a certain "specification"
    @GetMapping("users/{username}/specifications/{specification}/records")
    public ResponseEntity<List<GenericRecord>> getRecordsByUsernameAndSpecification(
            @PathVariable("username") String username, @PathVariable("specification") String specType){
        List<GenericRecord> recordsOfUserBySpec = genericRecordService.getRecordsByUsernameAndSpecType(username, specType);
        return ResponseEntity.status(HttpStatus.OK).body(recordsOfUserBySpec);
    }

    // Get all records of the user from one of the user's "flat-file"
    @GetMapping("users/{username}/flat-files/{source-file}/records")
    public ResponseEntity<List<GenericRecord>> getRecordsByUsernameAndSourceFile(
            @PathVariable("username") String username, @PathVariable("source-file") String sourceFileName){
        List<GenericRecord> recordsOfUserBySourceFile = genericRecordService.getRecordsByUsernameAndSourceFile(username, sourceFileName);
        return ResponseEntity.status(HttpStatus.OK).body(recordsOfUserBySourceFile);
    }

}
