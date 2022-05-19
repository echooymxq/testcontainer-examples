package org.easywheelsoft.testcontainer.liquibase.controller;

import lombok.extern.slf4j.Slf4j;
import org.easywheelsoft.testcontainer.liquibase.domain.User;
import org.easywheelsoft.testcontainer.liquibase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author echooymxq
 **/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Mono<User> findUserById(@PathVariable long id) {
        return userService.findById(id);
    }

    @GetMapping
    public Flux<User> selectAll() {
        return userService.selectAll();
    }

    @PostMapping
    public Mono<User> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

}
