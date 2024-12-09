package com.candidates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import com.candidates.utils.Constantes;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * Clase principal de la aplicación Spring Boot.
 * Esta clase se encarga de iniciar la API y escanear las entidades
 * definidas en el paquete especificado.
 * 
 * @author Bryan Núñez
 */
@SpringBootApplication
@EntityScan("${candidates.app.package.entities}")
public class CandidatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CandidatesApplication.class, args);
	}
	
	
	@Bean
	OpenAPI customOpenAPI() {
	    return new OpenAPI()
	    		.components(new Components().addSecuritySchemes(Constantes.SECURITY_SCHEME_NAME, createAPIKeyScheme()))
	    	        .info(createApiInfo());
	}

	private SecurityScheme createAPIKeyScheme() {
		return new SecurityScheme()
				.type(SecurityScheme.Type.HTTP)
				.bearerFormat(Constantes.BEARER_FORMAT)
				.scheme(Constantes.SCHEME);
	}

	private Info createApiInfo() {
		return new Info()
				.title(Constantes.API_TITLE)
				.description(Constantes.API_DESCRIPTION)
				.version(Constantes.API_VERSION)
				.contact(new Contact()
						.name(Constantes.CONTACT_NAME)
						.email(Constantes.CONTACT_EMAIL)
						.url(Constantes.CONTACT_URL));
	}
}
 