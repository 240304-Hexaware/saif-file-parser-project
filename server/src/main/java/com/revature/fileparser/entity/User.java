package com.revature.fileparser.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("users")
public class User {

    @Field("_id")
    @MongoId(FieldType.OBJECT_ID)
    private ObjectId id;

    @Field("username")
    private String username;

    @Field("password")
    private String password;

    @Field("adminStatus")
    private boolean adminStatus;

    @Field("permission")
    private boolean permission;

    public User() {
        this.adminStatus = false;
        this.permission = true;
    }

    public User(ObjectId id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.adminStatus = false;
        this.permission = true;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.adminStatus = false;
        this.permission = true;
    }

    public User(ObjectId id, String username, String password, boolean adminStatus, boolean permission) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.adminStatus = adminStatus;
        this.permission = permission;
    }

    public ObjectId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdminStatus() {
        return adminStatus;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdminStatus(boolean adminStatus) {
        this.adminStatus = adminStatus;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }
}
