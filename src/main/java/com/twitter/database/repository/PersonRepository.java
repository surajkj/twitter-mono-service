package com.twitter.database.repository;

import com.twitter.database.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Optional<Person> findById(Long id);

    List<Person> findAll();


}
