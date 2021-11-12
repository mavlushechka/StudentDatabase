package com.mavlushechka.studentdatabase.repository;

import com.mavlushechka.studentdatabase.domain.MilitaryService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilitaryServiceRepository extends MongoRepository<MilitaryService, String> {
}
