package com.fiap.parkmongoapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
		info = @Info(
				title = "Park Mongo API - PósTech FIAP",
				version = "1.0.0",
				description = "API de consulta de parquímetro - FIAP"
		)
)
public class ParkmongoapiApplication {



	public static void main(String[] args) {
		SpringApplication.run(ParkmongoapiApplication.class, args);
	}

}
