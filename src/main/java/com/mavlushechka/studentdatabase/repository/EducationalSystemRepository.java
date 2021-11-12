package com.mavlushechka.studentdatabase.repository;

import com.mavlushechka.studentdatabase.domain.EducationalSystem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationalSystemRepository extends MongoRepository<EducationalSystem, String> {
    Iterable<EducationalSystem> findByGroup(String group);
}
