package com.mavlushechka.studentdatabase.repository;

import com.mavlushechka.studentdatabase.domain.Health;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRepository extends MongoRepository<Health, String> {
}
