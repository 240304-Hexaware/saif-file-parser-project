package com.revature.fileparser.repository;

import com.revature.fileparser.entity.GenericRecord;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenericRecordRepository extends MongoRepository<GenericRecord, ObjectId> {

    List<GenericRecord> findByUsername(@Param("username") String username);

    List<GenericRecord> findByUsernameAndSpecType(@Param("username") String username, @Param("specification") String specType);

    List<GenericRecord> findByUsernameAndSourceFile(@Param("username") String username, @Param("sourceFile") String sourceFileName);

}