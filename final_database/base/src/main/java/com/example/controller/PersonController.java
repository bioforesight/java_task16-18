package com.example.controller;

import com.example.dto.Message;
import com.example.dto.Person;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public Iterable<Person> getPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/person/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return personService.getPersonById(id);
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable int id) {
        personService.deletePerson(id);
    }

    @GetMapping("/person/{id}/message")
    public List<Message> getMessages(@PathVariable int id) {
        return personService.getMessages(id);
    }

    @GetMapping("/person/{p_id}/message/{m_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable int p_id, @PathVariable int m_id) {
        return personService.getMessageById(p_id, m_id);
    }

    @PostMapping("/person/{id}/message")
    public ResponseEntity<Person> addMessageToPerson(@PathVariable int id, @RequestBody Message message) {
        return personService.addMessageToPerson(id, message);
    }

    @DeleteMapping("/person/{p_id}/message/{m_id}")
    public ResponseEntity<Person> deleteMessageFromPerson(@PathVariable int p_id, @PathVariable int m_id) {
        return personService.deleteMessageFromPerson(p_id, m_id);
    }
}