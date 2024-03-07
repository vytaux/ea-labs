package com.example.demo.repository;

import com.example.demo.entity.mongodb.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDbPersonRepository extends MongoRepository<Person, String>{
}
