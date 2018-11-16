package com.sxkl.project.cloudnote.flag.dao;

import com.sxkl.project.cloudnote.flag.entity.Flag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlagDao extends MongoRepository<Flag,String> {
}
