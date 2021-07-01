package com.mycompany.people.api_person.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mycompany.people.api_person.database.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO
{
    private Long id;

    @Enumerated(EnumType.STRING)
    private PhoneType type;

    @NotEmpty
    @Size(min = 10, max = 20)
    private String phone;
}
