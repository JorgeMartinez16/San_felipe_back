package com.proyecto.san_felipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;


@SpringBootApplication
@EnableMongoAuditing
public class SanFelipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SanFelipeApplication.class, args);
	}

}
