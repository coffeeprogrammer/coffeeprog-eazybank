package dev.coffeeprog.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans({
		@ComponentScan("dev.coffeeprog.controller"),
		@ComponentScan("dev.coffeeprog.config"),
		@ComponentScan("dev.coffeeprog.repository"),
		@ComponentScan("dev.coffeeprog.model")
})
public class EazybankApplication {

	public static void main(String[] args) {
		SpringApplication.run(EazybankApplication.class, args);
	}

}
