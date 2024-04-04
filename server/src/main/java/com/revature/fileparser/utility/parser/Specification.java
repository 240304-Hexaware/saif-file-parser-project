package com.revature.fileparser.utility.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Set;

public class Specification {

    private File specFile;
    private Map<String, Field> specMap;
    private String specStringJSON;


    public Specification(){

    }

    public Specification(File specFile) throws IOException {
        this.specFile = specFile;
        this.specStringJSON = new String(Files.readAllBytes(specFile.toPath())); // get the specification file data as a string
        ObjectMapper mapper = new ObjectMapper();
        this.specMap = mapper.readValue(specStringJSON, new TypeReference<Map<String, Field>>() {});
        Set<String> keySet = specMap.keySet();
        for(String s : keySet) {
            specMap.get(s).setName(s);
        }
    }

    public File getSpecFile() {
        return specFile;
    }

    public Map<String, Field> getSpecMap() {
        return specMap;
    }

    public String getSpecStringJSON() {
        return specStringJSON;
    }

    @Override
    public String toString() {
        return "Specification{\n" +
                " specMap= " + specMap + "\n" +
                " spec(as JSON)=\n  " + specStringJSON + "\n}";
    }

}