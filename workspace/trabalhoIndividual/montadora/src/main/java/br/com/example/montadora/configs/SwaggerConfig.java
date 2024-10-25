package br.com.example.montadora.configs;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@SecurityScheme(name="Bearer Auth", type=SecuritySchemeType.HTTP, scheme="bearer", bearerFormat="JWT")
public class SwaggerConfig {
	
	@Value("${prop.swagger.dev-url}")
	private String devUrl;
	
	@Bean
	public OpenAPI myOpenAPI() {
		Server server = new Server();
		server.setUrl(devUrl);
		server.setDescription("Development Server");
		
		Contact contact = new Contact();
		contact.setEmail("lucasmsarasa@gmail.com");
		contact.setName("Lucas Luiz Maia Sarasa");
		contact.setUrl("lucassarasa.net");
		
		License license = new License();
		license.setName("Apache License 2.0");
		license.setUrl("https://www.apache.org/licenses/LICENSE-2.0.html");
		
		Info info = new Info();
		info.setTitle("Carro & Conce API");
		info.setVersion("5.2.1");
		info.setDescription("API to manage carros and concessionarias");
		info.setContact(contact);
		info.setLicense(license);
		info.setTermsOfService("https://swagger.io/terms/");
		
		return new OpenAPI().info(info).servers(List.of(server));
	}
}
