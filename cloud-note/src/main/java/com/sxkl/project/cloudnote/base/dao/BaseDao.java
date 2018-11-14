package com.sxkl.project.cloudnote.base.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BaseDao<Entity,ID> extends MongoRepository<Entity, ID> {
}
