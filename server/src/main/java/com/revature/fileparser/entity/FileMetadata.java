package com.revature.fileparser.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("filemetadata")
public class FileMetadata {

    @Field("_id")
    @MongoId(FieldType.OBJECT_ID)
    private ObjectId id;

    @Field("fileName")
    private String fileName;

    @Field("size")
    private Integer size;

    // This is a self-made reference, WARNING: Do not use @Reference annotations like foreign keys as in SQL.
    // In MongoDB references are made to documents(i.e. the whole object itself rather than a field)
    // So treat them as normal fields but note them as reference for yourself.
    @Field("uploader")
    private String uploaderUsername;

    @Field("fileLocation")
    private String fileLocation;

    //private String date/time??;


    public FileMetadata(){

    }

    public FileMetadata(String fileName, Integer size, String uploaderUsername, String fileLocation) {
        this.fileName = fileName;
        this.size = size;
        this.uploaderUsername = uploaderUsername;
        this.fileLocation = fileLocation;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
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
