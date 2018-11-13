package com.sxkl.project.cloudgateway.user.service.impl;

import com.sxkl.project.cloudgateway.common.entity.ReactiveOperationResult;
import com.sxkl.project.cloudgateway.user.dao.UserDao;
import com.sxkl.project.cloudgateway.user.dao.UserPageDao;
import com.sxkl.project.cloudgateway.user.entity.User;
import com.sxkl.project.cloudgateway.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserPageDao userPageDao;
    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<ServerResponse> registe(ServerRequest request) {
        return request.bodyToMono(User.class)
               .flatMap(user -> {
                   Mono<User> userMono = userDao.findByName(user.getName());
                   return userMono.flatMap(bean->ServerResponse.status(HttpStatus.FORBIDDEN).body(ReactiveOperationResult.buildFail("用户已存在"),ReactiveOperationResult.class))
                           .switchIfEmpty(registe(user));
               })
               .switchIfEmpty(ServerResponse.badRequest().body(ReactiveOperationResult.buildFail("用户名密码不能为空"),ReactiveOperationResult.class));
    }

    private Mono<ServerResponse> registe(User user) {
        return userDao.save(user)
               .flatMap(bean->ServerResponse.ok().body(ReactiveOperationResult.buildSuccess("用户注册成功"),ReactiveOperationResult.class))
               .switchIfEmpty(ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ReactiveOperationResult.buildFail("用户注册失败"), ReactiveOperationResult.class));
    }

    @Override
    public Mono<ServerResponse> update(ServerRequest request) {
        return request.bodyToMono(User.class)
               .flatMap(user -> {
                   Query query = new Query(Criteria.where("id").is(user.getId()));
                   Update update = new Update();
                   update.set("name", user.getName());
                   update.set("password", user.getPassword());
                   reactiveMongoTemplate.updateFirst(query, update, User.class);
                   return ServerResponse.ok().body(ReactiveOperationResult.buildSuccess("用户更新成功"),ReactiveOperationResult.class);
               }).switchIfEmpty(ServerResponse.badRequest().body(ReactiveOperationResult.buildFail("数据不能为空"),ReactiveOperationResult.class));
    }

    @Override
    public Mono<ServerResponse> delete(ServerRequest request) {
        Optional<String> userIdOptional = request.queryParam("id");
        if(!userIdOptional.isPresent()) {
            ServerResponse.badRequest().body(ReactiveOperationResult.buildFail("用户ID不能为空"),ReactiveOperationResult.class);
        }
        Query query = new Query(Criteria.where("id").is(userIdOptional.get()));
        return reactiveMongoTemplate.remove(query,User.class)
                                    .flatMap(mono->ServerResponse.ok().body(ReactiveOperationResult.buildSuccess("用户删除成功"),ReactiveOperationResult.class));
    }

    @Override
    public Mono<ServerResponse> fingdOne(ServerRequest request) {
        Optional<String> userIdOptional = request.queryParam("id");
        if(!userIdOptional.isPresent()) {
            ServerResponse.badRequest().body(ReactiveOperationResult.buildFail("用户ID不能为空"),ReactiveOperationResult.class);
        }
        Query query = new Query(Criteria.where("id").is(userIdOptional.get()));
        Mono<User> userMono = reactiveMongoTemplate.findOne(query, User.class);
        return userMono.flatMap(user->ServerResponse.ok().body(userMono,User.class))
                       .switchIfEmpty(ServerResponse.notFound().build());
    }

    @Override
    public Mono<ServerResponse> login(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);
        return userMono.flatMap(user->{
                           return userDao.findOne(Example.of(user))
                                         .flatMap(bean -> {
//                                             request.session().subscribe(webSession -> {
//                                                 webSession.
//                                             });
                                             return ServerResponse.ok().body(ReactiveOperationResult.buildSuccess("用户登陆成功"),ReactiveOperationResult.class);
                                         })
                                         .switchIfEmpty(ServerResponse.badRequest().body(ReactiveOperationResult.buildFail("用户名密码不匹配"),ReactiveOperationResult.class));
                       })
                       .switchIfEmpty(ServerResponse.badRequest().body(ReactiveOperationResult.buildFail("用户名密码不能为空"),ReactiveOperationResult.class));
    }

    @Override
    public Mono<ServerResponse> findAll(ServerRequest request) {
        Flux<User> userFlux = reactiveMongoTemplate.findAll(User.class);
        return ServerResponse.ok().body(userFlux,User.class);
    }

    @Override
    public Mono<ServerResponse> findPage(ServerRequest request) {
        Optional<String> pageOptional = request.queryParam("page");
        Optional<String> sizeOptional = request.queryParam("size");
        int page = pageOptional.isPresent() ? Integer.parseInt(pageOptional.get()) : 0;
        int size = sizeOptional.isPresent() ? Integer.parseInt(sizeOptional.get()) : 20;
        Page<User> userPage = userPageDao.findAll(PageRequest.of(page, size));
        return ServerResponse.ok().body(Mono.just(userPage), Page.class);
    }
}
