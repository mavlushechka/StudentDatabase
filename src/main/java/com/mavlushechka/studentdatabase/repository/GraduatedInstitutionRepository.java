package com.mavlushechka.studentdatabase.repository;

import com.mavlushechka.studentdatabase.domain.GraduatedInstitution;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraduatedInstitutionRepository extends MongoRepository<GraduatedInstitution, String> {
}
