package com.example.service;

import com.example.dto.Message;
import com.example.dto.Person;
import com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public Iterable<Person> getAllPersons() {
        return repository.findAll();
    }

    public Optional<Person> getPersonById(int id) {
        return repository.findById(id);
    }

    public Person addPerson(Person person) {
        repository.save(person);
        return person;
    }

    public ResponseEntity<Person> updatePerson(int id, Person person) {
        Optional<Person> existingPerson = repository.findById(id);
        if (existingPerson.isPresent()) {
            Person personToUpdate = existingPerson.get();
            personToUpdate.setId(id);
            personToUpdate.setFirstname(person.getFirstname());
            personToUpdate.setLastname(person.getLastname());
            personToUpdate.setSurname(person.getSurname());
            personToUpdate.setBirthday(person.getBirthday());
            Person updatedPerson = repository.save(personToUpdate);
            return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public void deletePerson(int id) {
        repository.deleteById(id);
    }

    public List<Message> getMessages(int id) {
        Optional<Person> personOptional = repository.findById(id);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            return person.getMessages();
        } else {
            return List.of();
        }
    }

    public ResponseEntity<Message> getMessageById(int p_id, int m_id) {
        Optional<Person> personOptional = repository.findById(p_id);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            List<Message> messages = person.getMessages();
            if (messages != null)
                for (Message message : messages)
                    if (message.getId() == m_id)
                        return new ResponseEntity<>(message, HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Person> addMessageToPerson(int id, Message message) {
        Optional<Person> personOptional = repository.findById(id);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            List<Message> persons_messages = person.getMessages();
            persons_messages.add(message);
            repository.save(person);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<Person> deleteMessageFromPerson(int p_id, int m_id) {
        Optional<Person> personOptional = repository.findById(p_id);

        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            List<Message> personsMessages = person.getMessages();

            if (personsMessages != null) {
                personsMessages.removeIf(message -> message.getId() == m_id);
                person.setMessages(personsMessages);  
                repository.save(person); 
                return new ResponseEntity<>(person, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}