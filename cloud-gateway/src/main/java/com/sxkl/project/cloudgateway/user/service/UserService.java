package com.sxkl.project.cloudgateway.user.service;

import com.sxkl.project.cloudgateway.user.entity.User;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<ServerResponse> registe(ServerRequest request);

    Mono<ServerResponse> update(ServerRequest request);

    Mono<ServerResponse> delete(ServerRequest request);

    Mono<ServerResponse> fingdOne(ServerRequest request);

    Mono<ServerResponse> login(ServerRequest request);

    Mono<ServerResponse> findAll(ServerRequest request);

    Mono<ServerResponse> findPage(ServerRequest request);
}
