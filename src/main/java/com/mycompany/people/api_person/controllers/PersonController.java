package com.mycompany.people.api_person.controllers;

import com.mycompany.people.api_person.database.Person;
import com.mycompany.people.api_person.database.PersonRepository;
import com.mycompany.people.api_person.database.Phone;
import com.mycompany.people.api_person.database.PhoneType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**  Note: The API path at localhost is:
 *  https://localhost:9056/api/v1/people
 *
 *   =>> The default port is 9056.
 *------------------------------------------------*/
@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonRepository personRepository;

    // Dependency injection via constructor parameter.
    // Dependency injection means that an internal class attribute dependency is
    // instantiated by the calling code (External code) rather instead of
    // being instantiated by the class.
    @Autowired
    public PersonController(PersonRepository repository)
    {
        System.err.println(" [TRACE] Person controller instantiated. Ok. ");
        this.personRepository = repository;

        System.err.println(" [TRACE] Repository = " + repository);
    }

    // @GetMapping
    public String entrypoint()
    {
        return "Hello word Entrypoint.";
    }

    // Http method POST
    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public String create(@RequestBody Person person)
    {
        Person saved = personRepository.save(person);
        System.err.println(" [TRACE] Create object = " + person);
        return " Created person with ID = " + saved.getId();
    }

    /** Create some sample data for experimentation purposes. */
    public String createSampleData()
    {
        System.err.println(" [TRACE] Enter method .createSampleData() ");

        Person person = null;
        Phone phone  =null;
        ArrayList<Phone> phoneList = null;

        phone = Phone.builder().phone("9-981.745").type( PhoneType.HOME).build();
        phoneList = new ArrayList<>();
        phoneList.add(phone);
        person = Person.builder()
                .firstName("Juan")
                .lastName("Pablo Gutierrez")
                .cpf("99152334213")
                .phones( phoneList )
                .dateOfBirth( LocalDate.of(1990, 6, 12) )
                .build();
        this.personRepository.save(person);

        phone = Phone.builder().phone("9-992.725").type( PhoneType.COMMERCIAL).build();
        phoneList = new ArrayList<>();
        phoneList.add(phone);
        person = Person.builder()
                .firstName("Pedro")
                .lastName("Cavalcante Baltazar")
                .cpf("25246234324")
                .phones( phoneList )
                .dateOfBirth( LocalDate.of(2000, 1, 25) )
                .build();
        this.personRepository.save(person);

        System.err.println(" [TRACE] Successful");

        return "Success";
    }
}
