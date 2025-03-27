package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.dto.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
}