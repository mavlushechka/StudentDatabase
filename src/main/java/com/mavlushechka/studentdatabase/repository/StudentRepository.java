package com.mavlushechka.studentdatabase.repository;

import com.mavlushechka.studentdatabase.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
}
