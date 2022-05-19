package org.easywheelsoft.testcontainer.liquibase;

import org.easywheelsoft.testcontainer.liquibase.domain.User;
import org.easywheelsoft.testcontainer.liquibase.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author echooymxq
 **/
@Testcontainers
@SpringBootTest
class LiquibaseWithTestContainerApplicationITests {

    @Container
    private static final MySQLContainer<?> container = new MySQLContainer<>("mysql:5.6");

    @DynamicPropertySource
    static void mySQLProperties(DynamicPropertyRegistry registry) {
        String jdbcUrl = container.getJdbcUrl();
        registry.add("spring.liquibase.url", () -> jdbcUrl);
        registry.add("spring.liquibase.user", container::getUsername);
        registry.add("spring.liquibase.password", container::getPassword);
        registry.add("spring.liquibase.changeLog", () -> "classpath:/db/changelog.xml");
        registry.add("spring.r2dbc.url", () -> jdbcUrl.replace("jdbc", "r2dbc"));
        registry.add("spring.r2dbc.username", container::getUsername);
        registry.add("spring.r2dbc.password", container::getPassword);
    }

    @Autowired
    private UserRepository userRepository;

    @Test
    void testAdd() {
        User user = User.builder().name("echooymxq").build();
        Mono<User> save = userRepository.save(user);
        StepVerifier.create(save)
                .expectNextMatches(user1 -> user1.getName().equals("echooymxq"))
                .verifyComplete();
    }

}
