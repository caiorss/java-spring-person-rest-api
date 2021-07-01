package com.mycompany.people.api_person.dto;

/* Note: It requires the following gradle dependencies:
 *   + implementation 'javax.validation:validation-api'
 *   + implementation 'org.hibernate.validator:hibernate-validator'
 */
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO
{
    private  Long id;

    @NotEmpty(message = "First name is mandatory")
    @Size(min = 2, max = 200)
    private String firstName;

    @NotEmpty()
    @Size(min = 2, max = 200)
    private String lastName;

    // CPF - Brazil's taxpayer number, akin to American SSN (Social Security Number)
    // CPF stands for Cadastro de Pessoas Fisicas (Portuguese).
    //
    @NotEmpty
    @CPF
    private String cpf;

    @NotNull
    private String dateOfBirth;

    @Valid
    @NotEmpty
    private List<PhoneDTO> phones;
}
