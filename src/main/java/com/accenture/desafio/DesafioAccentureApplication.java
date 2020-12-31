package com.accenture.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.accenture.desafio.config.FileStorageConfig;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageConfig.class
})
public class DesafioAccentureApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioAccentureApplication.class, args);
	}
}
