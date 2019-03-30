package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientsRepository extends MongoRepository<Client, String>, BaseRepository<Client, String> {

}