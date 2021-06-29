package com.mycompany.people.api_person.database;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneType
{
      // Home phone (telefone residencial)
      HOME("HOME")
      // Telefone comercial
    , COMMERCIAL("COMMERCIAL")
      // Telefone celular
    , MOBILE("MOBILE");

    private final String type;
}
