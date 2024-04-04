package com.revature.fileparser.repository;

import com.revature.fileparser.entity.SimpleSpecification;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimpleSpecificationRepository extends MongoRepository<SimpleSpecification, ObjectId> {

    List<SimpleSpecification> findByUploaderUsername(@Param("uploaderUsername") String uploaderUsername);
}
