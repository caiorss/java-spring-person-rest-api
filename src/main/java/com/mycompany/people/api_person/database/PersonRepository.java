package com.mycompany.people.api_person.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// Person is the type of data managed by the repository
// Long is the type of id (primary key) used by the repository
public interface PersonRepository extends JpaRepository<Person, Long>
{
    // Query using JPQL ( Java Persistence Query Language)
    @Query("SELECT p FROM Person p WHERE lower(p.lastName) LIKE lower( concat('%', :lastName, '%') ) ")
    List<Person> findPersonByLastName(@Param("lastName") String lastName );
}
