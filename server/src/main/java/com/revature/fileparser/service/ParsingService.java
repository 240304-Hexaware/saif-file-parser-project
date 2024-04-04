package com.revature.fileparser.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.revature.fileparser.entity.GenericRecord;
import com.revature.fileparser.utility.parser.GenericRecordGenerator;
import com.revature.fileparser.utility.parser.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Takes a flat-file (where data is stored contiguously with fixed record size and with no delimiters) and
 * a specification file (in JSON format). Parses the flat-file to generate a list of records where each
 * record is stored as a map (field name : field value).
 */
@Service
public class ParsingService {

    private File recordFile;
    private File specFile;
    private String recordFileName;
    private String specFileName;
    private String username;
    private Specification spec;
    private GenericRecordGenerator genericRecordGenerator;

    @Autowired
    public ParsingService() {
    }

    public ParsingService(File recordFile) {
        this.recordFile = recordFile;
    }

    public ParsingService(File recordFile, File specFile, String recordFileName,
        String specFileName) throws IOException {
        this.recordFile = recordFile;
        this.specFile = specFile;
        this.recordFileName = recordFileName;
        this.specFileName = specFileName;
        this.spec = new Specification(specFile);
        this.genericRecordGenerator = new GenericRecordGenerator(recordFile, spec);
    }

    public File getRecordFile() {
        return recordFile;
    }

    public void setRecordFile(File recordFile) {
        this.recordFile = recordFile;
    }

    public File getSpecFile() {
        return specFile;
    }

    public void setSpecFile(File specFile) {
        this.specFile = specFile;
    }

    public String getRecordFileName() {
        return recordFileName;
    }

    public void setRecordFileName(String recordFileName) {
        this.recordFileName = recordFileName;
    }

    public String getSpecFileName() {
        return specFileName;
    }

    public void setSpecFileName(String specFileName) {
        this.specFileName = specFileName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the specification as a single String in JSON format.
     * Assumption: All specification files will be provided as JSON format.
     * @return the specification String
     */
    public String getSpecJSON() {
        return spec.getSpecStringJSON();
    }

    /**
     * Returns a list of records where each record is stored as a map.
     * @return the List of Maps
     */
    public List<Map<String, String>> getRecordMapList() {
        return genericRecordGenerator.getRecordMapList();
    }

    /**
     * Returns a list of records where each record is a GenericRecord.
     * @return a List of GenericRecords
     */
    public List<GenericRecord> getRecordList() {
        return genericRecordGenerator.getRecordList();
    }

    public Specification getSpec() {
        return spec;
    }

    public void setSpec(Specification spec) {
        this.spec = spec;
    }

    public GenericRecordGenerator getGenericRecordGenerator() {
        return genericRecordGenerator;
    }

    public void setGenericRecordGenerator(GenericRecordGenerator genericRecordGenerator) {
        this.genericRecordGenerator = genericRecordGenerator;
    }

    public void parseIntoGeneralRecords() {
        this.genericRecordGenerator.generateRecordList(this.username, this.recordFileName, this.specFileName);
        this.genericRecordGenerator.generateGeneralRecordList();
    }

}
