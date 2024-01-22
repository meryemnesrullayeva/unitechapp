package com.app.currencyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@OpenAPIDefinition(info = @Info(title = "Unitech  project", version = "1.0", description = "Unitech Project"))
@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class CurrencyApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(CurrencyApiApplication.class, args);
	}

}
