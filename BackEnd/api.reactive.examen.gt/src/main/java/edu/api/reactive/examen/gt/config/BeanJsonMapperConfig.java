package edu.api.reactive.examen.gt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.json.JsonMapper;

@Configuration
public class BeanJsonMapperConfig {

	@Bean
	public JsonMapper getJsonMapper() {
		return new JsonMapper();
	}
}
