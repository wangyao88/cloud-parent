package com.sxkl.project.cloudgateway.user.service;

import com.sxkl.project.cloudgateway.common.entity.OperationResult;
import com.sxkl.project.cloudgateway.user.entity.User;

import java.util.List;

public interface UserService {

    OperationResult registe(User user);

    OperationResult update(User user);

    OperationResult delete(User user);

    User fingdOne(Long id);

    List<User> findAll();

    List<User> findPage(int index, int size, User user);
}
