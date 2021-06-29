package com.mycompany.people.api_person.database;

import org.springframework.data.jpa.repository.JpaRepository;

// Person is the type of data managed by the repository
// Long is the type of id (primary key) used by the repository
public interface PersonRepository extends JpaRepository<Person, Long>
{
}
