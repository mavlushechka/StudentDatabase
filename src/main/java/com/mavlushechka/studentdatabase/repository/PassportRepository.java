package com.mavlushechka.studentdatabase.repository;

import com.mavlushechka.studentdatabase.domain.Passport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportRepository extends MongoRepository<Passport, String> {
    Iterable<Passport> findByFirstName(String firstName);
    Iterable<Passport> findByFirstNameAndLastName(String firstName, String lastName);
}
