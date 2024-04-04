package com.revature.fileparser.repository;

import com.revature.fileparser.entity.FileMetadata;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends MongoRepository<FileMetadata, ObjectId> {

    List<FileMetadata> findByUploaderUsername(@Param("uploaderUsername") String uploaderUsername);
}