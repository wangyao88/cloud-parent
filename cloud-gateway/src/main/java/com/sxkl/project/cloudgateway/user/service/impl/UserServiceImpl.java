package com.sxkl.project.cloudgateway.user.service.impl;

import com.mongodb.client.result.UpdateResult;
import com.sxkl.project.cloudgateway.common.entity.OperationResult;
import com.sxkl.project.cloudgateway.user.dao.UserDao;
import com.sxkl.project.cloudgateway.user.entity.User;
import com.sxkl.project.cloudgateway.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ReactiveMongoTemplate mongoTemplate;


    @Override
    public OperationResult registe(User user) {
        try {
            Mono<Boolean> existsMono = userDao.exists(Example.of(user));
            existsMono.doAfterSuccessOrError((exists,throwable)->{
                if(exists) {
                    return OperationResult.buildFail("用户已存在");
                }
                Mono<User> save = userDao.save(user);
                return OperationResult.buildSuccess("用户注册成功");
            })
            if(exists) {
                return OperationResult.buildFail("用户已存在");
            }
            userDao.save(user);
            return OperationResult.buildSuccess("用户注册成功");
        } catch (Exception e) {
            return OperationResult.buildFail("用户注册失败", e);
        }
    }

    @Override
    public OperationResult update(User user) {
        try {
            Query query = new Query(Criteria.where("id").is(user.getId()));
            Update update = new Update();
            update.set("name", user.getName());
            update.set("password", user.getPassword());
            Mono<UpdateResult> updateResultMono = mongoTemplate.updateFirst(query, update, User.class);
            return OperationResult.buildSuccess("用户更新成功");
        } catch (Exception e) {
            return OperationResult.buildFail("用户更新失败", e);
        }
    }

    @Override
    public OperationResult delete(User user) {
    }

    @Override
    public User fingdOne(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findPage(int index, int size, User user) {
        return null;
    }
}
