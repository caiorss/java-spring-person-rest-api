package com.mycompany.people.api_person.database;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneType
{

      HOME("HOME")
    , COMMERCIAL("COMMERCIAL")
    , MOBILE("MOBILE");

    private final String type;
}
