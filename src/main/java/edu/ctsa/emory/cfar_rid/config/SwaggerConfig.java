package edu.ctsa.emory.cfar_rid.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CFAR RID API Documentation")
                        .version("1.0.0")
                        .description("This is the API documentation for the CFAR RID backend application.")
                        .contact(new Contact()
                                .name("Venkatesh Javvaji")
                                .email("vjavvaj@emory.edu")
                                .url("https://med.emory.edu/departments/biomedical-informatics/people/staff.html"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }

}
