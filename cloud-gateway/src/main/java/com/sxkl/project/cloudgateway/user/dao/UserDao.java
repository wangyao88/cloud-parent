package com.sxkl.project.cloudgateway.user.dao;

import com.sxkl.project.cloudgateway.user.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserDao extends ReactiveMongoRepository<User, ObjectId> {

    Mono<User> findByName(String name);
}
