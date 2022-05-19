package org.easywheelsoft.testcontainer.liquibase.service;

import org.easywheelsoft.testcontainer.liquibase.domain.User;
import org.easywheelsoft.testcontainer.liquibase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author echooymxq
 **/
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Mono<User> findById(long id) {
        return userRepository.findById(id);
    }

    public Mono<User> addUser(User user) {
        return userRepository.save(user);
    }

    public Flux<User> selectAll() {
        return userRepository.findAll();
    }

}
