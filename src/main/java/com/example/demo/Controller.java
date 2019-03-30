package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    private final ClientsRepository repository;

    @Autowired
    public Controller(ClientsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {

        return repository.mySearch(new Query(), PageRequest.of(0, 1)).getContent();
    }
}
