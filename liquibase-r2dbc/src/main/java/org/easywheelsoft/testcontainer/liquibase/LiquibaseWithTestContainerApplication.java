package org.easywheelsoft.testcontainer.liquibase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 *
 * @author <a href="mailto:echooy.mxq@gmail.com">echooymxq</a>
 **/
@EnableR2dbcRepositories
@SpringBootApplication
public class LiquibaseWithTestContainerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiquibaseWithTestContainerApplication.class, args);
	}

}
