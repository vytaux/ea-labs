package com.example.demo.repository;

import com.example.demo.entity.hypersql.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HyperSqlPersonRepository extends JpaRepository<Person, Long>{
}
