package com.sxkl.project.cloudgateway.user.dao;

import com.sxkl.project.cloudgateway.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserDao extends ReactiveMongoRepository<User, Long> {
}
