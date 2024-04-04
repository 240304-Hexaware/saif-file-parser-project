package com.revature.fileparser.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Map;

@Document("parsedrecords")
public class GenericRecord extends org.bson.Document{

    @Field("_id")
    @MongoId(FieldType.OBJECT_ID)
    private ObjectId id;

  // The following 3 fields will be used for referencing purposes.
    @Field("user")
    private String username;

    @Field("sourceFile")
    private String sourceFile;

    @Field("specification")
    private String specType;

    public GenericRecord() {
    }

    public GenericRecord(Map<String, String> map) {
        super(map);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String getSpecType() {
        return specType;
    }

    public void setSpecType(String specType) {
        this.specType = specType;
    }
}