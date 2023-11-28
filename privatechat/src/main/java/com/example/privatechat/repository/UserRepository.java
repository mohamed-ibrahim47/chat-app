package com.example.privatechat.repository;

import com.example.privatechat.domain.Status;
import com.example.privatechat.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    List<User> findAllByStatus(Status status);
}
