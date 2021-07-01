package com.mycompany.people.api_person.dto;

// Requires the gradle dependency
//  GroupId = org.mapstruct / ArtifactID = mapstruct
//-----------------------------------------------------
import com.mycompany.people.api_person.database.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring" )
public interface PersonMapper
{
    // Note: dateOfBirth (data de nascimento - in Portuguse) is in the format ISO 8601
    // where yyyy is the year, MM is the month (numeric) 2 digits and dd is the date.
    @Mapping(target = "dateOfBirth", source = "dateOfBirth", dateFormat = "yyyy-MM-dd")
    Person toModel(PersonDTO dto);

    PersonDTO toDto(PersonDTO dto);
}
