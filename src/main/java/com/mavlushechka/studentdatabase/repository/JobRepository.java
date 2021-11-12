package com.mavlushechka.studentdatabase.repository;

import com.mavlushechka.studentdatabase.domain.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends MongoRepository<Job, String> {
}
