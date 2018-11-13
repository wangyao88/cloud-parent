package com.sxkl.project.cloudgateway.user.dao;

import com.sxkl.project.cloudgateway.user.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserPageDao extends PagingAndSortingRepository<User, ObjectId> {
}
