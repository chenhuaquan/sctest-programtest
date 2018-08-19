package com.sc.test.programtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@ComponentScan("com.sc.test.programtest")
@EntityScan("com.sc.test.programtest")
@EnableAutoConfiguration
@EnableScheduling
public class ProgramtestApplication {
    private static final Logger logger = LoggerFactory.getLogger(ProgramtestApplication.class);

	public static void main(String[] args) {
        String className = ProgramtestApplication.class.getName();
        logger.info("application {} begin......", className);
        SpringApplication.run(ProgramtestApplication.class, args);
        logger.info(" application {} started finished.", className);
	}
}
