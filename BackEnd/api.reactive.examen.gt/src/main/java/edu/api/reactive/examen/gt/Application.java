package edu.api.reactive.examen.gt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(info = @Info(title = "API Examen Reactives Application GT", version = "1.0", description = "Documentacion de API Examen de Aplicaciones Reactivas", contact = @Contact(name = "David Cruz Huertas", email = "dach_092@hotmail.com"), license = @License(name = "Terminos de uso")))

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}

}
