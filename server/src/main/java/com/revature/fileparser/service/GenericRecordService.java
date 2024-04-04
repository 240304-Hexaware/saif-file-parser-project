package com.revature.fileparser.service;

import com.revature.fileparser.entity.GenericRecord;
import com.revature.fileparser.repository.GenericRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericRecordService {

    private GenericRecordRepository genericRecordRepository;

    @Autowired
    public GenericRecordService(GenericRecordRepository genericRecordRepository) {
        this.genericRecordRepository = genericRecordRepository;
    }

    public GenericRecord persistRecord(GenericRecord record){
        return genericRecordRepository.save(record);
    }

    public List<GenericRecord> persistAllRecords(List<GenericRecord> recordList){
        return genericRecordRepository.saveAll(recordList);
    }

    public List<GenericRecord> getRecordsByUsername(String username){
        return genericRecordRepository.findByUsername(username);
    }

    public List<GenericRecord> getRecordsByUsernameAndSpecType(String username, String specType){
        return genericRecordRepository.findByUsernameAndSpecType(username, specType);
    }

    public List<GenericRecord> getRecordsByUsernameAndSourceFile(String username, String sourceFileName) {
        return genericRecordRepository.findByUsernameAndSourceFile(username, sourceFileName);
    }
}

