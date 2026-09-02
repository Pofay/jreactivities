package com.giancarlo.gilos.jreactivities;

import org.springframework.boot.SpringApplication;

public class TestJreactivitiesApplication {

	public static void main(String[] args) {
		SpringApplication.from(JreactivitiesApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
