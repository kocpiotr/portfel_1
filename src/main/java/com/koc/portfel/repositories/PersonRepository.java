package com.koc.portfel.repositories;

import com.koc.portfel.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
