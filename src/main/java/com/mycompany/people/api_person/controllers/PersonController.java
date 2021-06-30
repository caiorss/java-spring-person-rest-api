package com.mycompany.people.api_person.controllers;

import com.mycompany.people.api_person.database.Person;
import com.mycompany.people.api_person.database.PersonRepository;
import com.mycompany.people.api_person.database.Phone;
import com.mycompany.people.api_person.database.PhoneType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Class is placed in this file for avoiding code bloat.
// Note: It is not a checked exception
@ResponseStatus(HttpStatus.NOT_FOUND)
class Exception_PersonNotFound extends Exception
{
    public Exception_PersonNotFound(Long id)
    {
        super(String.format(" [ERROR] Unable to find person which id is %d ", id));
    }
}

/**  Note: The API path at localhost is:
 *  https://localhost:9056/api/v1/people
 *
 *   =>> The default port is 9056.
 *------------------------------------------------*/
@RestController
@RequestMapping("/api/v1/people")
public class PersonController
{

    // Note: Initialized by dependency injection through constructor.
    private PersonRepository personRepository = null;

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

    @GetMapping
    public List<Person> listAll()
    {
        List<Person> all = personRepository.findAll();
        System.err.println(" [TRACE] All people = \n" + all);
        return all;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Person findById(@PathVariable Long id) throws Exception_PersonNotFound
    {
        Optional<Person> opt = this.personRepository.findById(id);
        if (opt.isEmpty()) {
            throw new Exception_PersonNotFound(id);
        }
        return opt.get();
    }

    // Http method POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Person person)
    {
        Person saved = personRepository.save(person);
        System.err.println(" [TRACE] Create object = " + person);
        return " Created person with ID = " + saved.getId();
    }

    // Http method DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id)
    {
        System.err.printf(" [TRACE] Deleting item id = %d \n", id);
        this.personRepository.deleteById(id);
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
