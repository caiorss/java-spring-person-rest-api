package com.mycompany.people.api_person.database;

// Import Entity, Id,
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone
{
    // Primary key
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column( nullable = false)
    private PhoneType type = PhoneType.HOME;

    @Column( nullable = false )
    private String phone;
}
