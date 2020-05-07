package org.easywheelsoft.testcontainer.redis;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author <a href="mailto:echooy.mxq@gmail.com">echooymxq</a>
 */
@ActiveProfiles("test")
@SpringBootTest
class TestContainerExamplesApplicationTests {

	@Autowired
	private ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

	@Test
	void contextLoads() {
		Mono<Boolean> operation = reactiveRedisTemplate.opsForValue().set("test_key", "val");

		Mono<String> result = operation.then(
				reactiveRedisTemplate.opsForValue().get("test_key"));

		StepVerifier.create(result)
				.expectNext("val")
				.expectComplete().verify();
	}

}

