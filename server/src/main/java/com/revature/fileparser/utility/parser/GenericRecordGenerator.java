package com.revature.fileparser.utility.parser;

import com.revature.fileparser.entity.GenericRecord;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;


public class GenericRecordGenerator {

    private Specification spec;
    private File recordFile;
    private String recordFileData;
    private Integer recordFileSize;
    private Integer recordSize;
    private Integer numberOfRecords;
    private List<Map<String, String>> recordMapList;
    private List<GenericRecord> recordList;

    public GenericRecordGenerator(){

    }

    public GenericRecordGenerator(File recordFile, Specification spec) throws IOException {
        this.spec = spec;
        this.recordFile = recordFile;
        this.recordFileData = new String(Files.readAllBytes(recordFile.toPath()));
        this.recordFileSize = recordFileData.length();
        String lastKey = spec.getSpecMap().keySet().stream().reduce((first, second) -> second).orElse(null);
        this.recordSize = spec.getSpecMap().get(lastKey).getEndPos() + 1;
        this.numberOfRecords = recordFileSize/recordSize;
        this.recordMapList = new ArrayList<>();
        this.recordList = new ArrayList<>();
    }

    public String getRecordFileData() {
        return recordFileData;
    }

    public Integer getRecordFileSize() {
        return recordFileSize;
    }

    public Integer getRecordSize() {
        return recordSize;
    }

    public Integer getNumberOfRecords() {
        return numberOfRecords;
    }

    public List<Map<String,String>> getRecordMapList(){
        return recordMapList;
    }

    public List<GenericRecord> getRecordList() {
        return recordList;
    }

    @Override
    public String toString() {
        return "GenericRecordGenerator {\n" +
                "  recordFileData=" + recordFileData +
                " \n  recordFileSize=" + recordFileSize +
                " \n  recordSize=" + recordSize +
                " \n  numberOfRecords=" + numberOfRecords +
                " }";
    }

    public void generateRecordList(String user, String sourceFileName, String specType){
        Set<String> fieldsSet = spec.getSpecMap().keySet();
        for(int i=0; i<numberOfRecords; i++) {
            String record = recordFileData.substring(i*recordSize, (i+1)*recordSize); // get a single record
            Map<String, String> recordMap = new LinkedHashMap<>();
            for(String field: fieldsSet) {
                String fieldValue = record.substring(spec.getSpecMap().get(field).getStartPos(),
                        spec.getSpecMap().get(field).getEndPos()+1).trim();
                recordMap.put(field, fieldValue); // generate the record as a map
            }
            // Add this extra fields as reference to other 3 collections
            recordMap.put("user", user);
            recordMap.put("sourceFile", sourceFileName);
            recordMap.put("specification", specType);
            recordMapList.add(recordMap); // add the record to a list to get the list of records
        }
    }

    public void generateGeneralRecordList() {
        for(Map<String, String> recordMap: recordMapList){
            recordList.add(new GenericRecord(recordMap));
        }
    }
}
