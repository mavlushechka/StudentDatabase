package com.mavlushechka.studentdatabase.repository;

import com.mavlushechka.studentdatabase.domain.Family;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends MongoRepository<Family, String> {
}
