package com.example.controller;

import com.example.dto.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MessageController {
    @Autowired
    private MessageRepository repository;

    @GetMapping("/message")
    public Iterable<Message> getMessages() {
        return repository.findAll();
    }

    @GetMapping("/message/{id}")
    public Optional<Message> findMessageById(@PathVariable int id) {
        return repository.findById(id);
    }
    @PostMapping("/message")
    public Message addMessage(@RequestBody Message message) {
        repository.save(message);
        return message;
    }
    @PutMapping("/message/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {
        Optional<Message> existingMessage = repository.findById(id);
        if (existingMessage.isPresent()) {
            Message messageToUpdate = existingMessage.get();
            messageToUpdate.setId(id);
            messageToUpdate.setTitle(message.getTitle());
            messageToUpdate.setText(message.getText());
            messageToUpdate.setTime(message.getTime());
            Message updatedMessage = repository.save(messageToUpdate);
            return new ResponseEntity<>(updatedMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/message/{id}")
    public void deleteMessage(@PathVariable int id) {
        repository.deleteById(id);
    }
}
