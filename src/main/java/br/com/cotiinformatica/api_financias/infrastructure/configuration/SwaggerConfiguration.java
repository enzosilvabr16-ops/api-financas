package br.com.cotiinformatica.api_financias.infrastructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI apiDocumentation() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Finanças - COTI Informática")
                        .version("1.0.0")
                        .description("API para gerenciamento de movimentações financeiras.")
                        .contact(new Contact().name("COTI Informática"))
                );
    }
}