package org.prog.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonsJpa extends JpaRepository<Persons, Long> {

    List<Persons> findAllByFirstName(String name);

    Optional<Persons> findAllByLastName(String lastName);
}