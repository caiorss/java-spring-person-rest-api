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
    // @Mapping(target = "dateOfBirth", source = "dateOfBirth", dateFormat = "yyyy-MM-dd")
    Person toModel(PersonDTO dto);

    PersonDTO toDto(PersonDTO dto);
}
