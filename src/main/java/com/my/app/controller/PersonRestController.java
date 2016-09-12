package com.my.app.controller;

import com.my.app.model.Contact;
import com.my.app.model.Person;
import com.my.app.repository.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mgiec on 9/8/2016.
 */
@RestController
@RequestMapping("/rest/person")
public class PersonRestController {

    /*
    Specifically, @GetMapping is a composed annotation that acts as
    a shortcut for @RequestMapping(method = RequestMethod.GET).
     */
    @Autowired
    private PersonDao personDao;

    @GetMapping("/all")
    public ResponseEntity<List<Person>> findAll() {
        List<Person> people = personDao.findAll();
        if (people.isEmpty()) {
            return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Person>>(people, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getUser(@PathVariable("id") long id) {
        Person person = personDao.findbyIdWithContacts(id);
        if (person == null) {
            System.out.println("Not Found");
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public ModelAndView showPersonForm(){
//        ModelAndView mav = new ModelAndView("restPerson");
//        mav.addObject("nperson", new Person());
//        return mav;
//    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        personDao.savePerson(person);
        if (person.getId() == 0) {
            return new ResponseEntity<Person>(person, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Person>(person, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/merge", method = RequestMethod.POST)
    public ResponseEntity<Person> mergePerson(@RequestBody Person person, @RequestBody Contact contact) {
        person.getContacts().add(contact);
        personDao.mergePerson(person);
        if (person.getId() == 0) {
            return new ResponseEntity<Person>(person, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Person>(person, HttpStatus.CREATED);
    }
}


