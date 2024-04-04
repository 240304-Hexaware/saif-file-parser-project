package com.revature.fileparser.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("specifications")
public class SimpleSpecification {

    @Field("_id")
    @MongoId(FieldType.OBJECT_ID)
    private ObjectId id;

    @Field("specFileName")
    private String specFileName;

    @Field("specAsJSON")
    private String specAsJSON; // Assuming all specFiles are in JSON format

    @Field("uploader")
    private String uploaderUsername;

    @Field("fileLocation")
    private String fileLocation;

    public SimpleSpecification() {

    }

    public SimpleSpecification(String specFileName, String specAsJSON, String uploaderUsername, String fileLocation) {
        this.specFileName = specFileName;
        this.specAsJSON = specAsJSON;
        this.uploaderUsername = uploaderUsername;
        this.fileLocation = fileLocation;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getSpecFileName() {
        return specFileName;
    }

    public void setSpecFileName(String specFileName) {
        this.specFileName = specFileName;
    }

    public String getSpecAsJSON() {
        return specAsJSON;
    }

    public void setSpecAsJSON(String specAsJSON) {
        this.specAsJSON = specAsJSON;
    }

    public String getUploaderUsername() {
        return uploaderUsername;
    }

    public void setUploaderUsername(String uploaderUsername) {
        this.uploaderUsername = uploaderUsername;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }
}
