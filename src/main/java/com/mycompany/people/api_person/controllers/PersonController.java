package com.mycompany.people.api_person.controllers;

import com.mycompany.people.api_person.database.Person;
import com.mycompany.people.api_person.database.PersonRepository;
import com.mycompany.people.api_person.database.Phone;
import com.mycompany.people.api_person.database.PhoneType;
import com.mycompany.people.api_person.dto.PersonDTO;
import com.mycompany.people.api_person.dto.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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
// @AllArgsConstructor( onConstructor = @__(@Autowired))
public class PersonController
{

    // Note: Initialized by dependency injection through constructor.
    // Note: The 'final' keyword means that the reference to object
    //       can only be initialized only once, it is similar to C++ const pointer
    //       or to C++ const reference. Variables  annotated with final must be
    //       initialized by all class' constructors.
    private final PersonRepository personRepository ;
    private PersonMapper personMapper;

    // Dependency injection via constructor parameter.
    // Dependency injection means that an internal class attribute dependency is
    // instantiated by the calling code (External code) rather instead of
    // being instantiated by the class.
    @Autowired
    public PersonController(PersonRepository repository, PersonMapper mapper)
    {
        System.err.println(" [TRACE] Person controller instantiated. Ok. ");
        this.personRepository = repository;
        this.personMapper = mapper;

        // Assertions for checking and enforcing assumptions.
        assert this.personRepository != null;
        assert this.personMapper != null;

        System.err.println(" [TRACE] Repository = " + repository);
    }

    // Http method GET
    // @RequestParam( name = "name", ...) String query => Is the endpoint query
    // parameter /api/v1/people?lastName=<SOMETHING>
    @GetMapping
    public List<Person> listAll( @RequestParam( name = "lastName", required = false) String firstName  )
    {
        // Return all registered users
        if( firstName == null || firstName.isEmpty() ) {
            List<Person> all = personRepository.findAll();
            System.err.println(" [TRACE] All people = \n" + all);
            return all;
        }
        System.err.println(" [TRACE] Searching user which name is like " + firstName );
        // Search user by first name
        return personRepository.findPersonByLastName(firstName);
    }

    // Note: This method returns 'Person' instead of DTO since it is assumed that
    // the data in the database is already validated.
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Person findById(@PathVariable Long id) throws Exception_PersonNotFound
    {
        Optional<Person> opt = this.personRepository.findById(id);

        if (opt.isEmpty()) {
            // throw new Exception_PersonNotFound(id);
            throw new ResponseStatusException( HttpStatus.NOT_FOUND
                                              , String.format("No resource found for id (%s)", id));
        }
        return opt.get();
    }

    // Http method POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody @Valid PersonDTO personDTO)
    {
        assert personMapper != null;
        Person person = personMapper.toModel(personDTO);
        System.err.println(" [TRACE] PersonDTO = " + personDTO);
        System.err.println(" [TRACE] Person = " + person);
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

    // Note: It performs full update
    // http method PUT => Action update database row.
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws Exception_PersonNotFound
    {
        System.err.println(" [TRACE] PersonController.update() => PersonDTO = " + personDTO);
        if( personRepository.findById(id).isEmpty() )
        {
            throw new Exception_PersonNotFound(id);
        }
        Person entity = personRepository.getById(id);
        Person person = personMapper.toModel(personDTO);
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setCpf(person.getCpf());
        entity.setPhones(person.getPhones());
        personRepository.save(entity);
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
