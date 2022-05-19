package org.easywheelsoft.testcontainer.liquibase.repository;

import org.easywheelsoft.testcontainer.liquibase.domain.User;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author echooymxq
 **/
@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
}
