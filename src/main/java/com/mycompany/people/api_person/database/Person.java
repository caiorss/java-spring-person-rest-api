package com.mycompany.people.api_person.database;

// Import Entity, Id,
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person
{
    // Database's primary key
    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private String firstName;

    @Column( nullable = false)
    private String lastName;

    // Brazilian CPF (Cadastro de Pessoas Fisicas) - equivalent to
    // American SSN (Social Security Number)
    @Column( nullable = false, unique = true)
    private String cpf;

    @Column( nullable = false)
    private LocalDate dateOfBirth;

    @OneToMany( fetch = FetchType.LAZY, cascade = {  CascadeType.PERSIST , CascadeType.MERGE , CascadeType.REMOVE })
    private List<Phone> phones;
}


