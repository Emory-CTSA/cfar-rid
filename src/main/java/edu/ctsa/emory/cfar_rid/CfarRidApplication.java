package edu.ctsa.emory.cfar_rid;

import edu.ctsa.emory.cfar_rid.repository.CFARDemographicsRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
public class CfarRidApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfarRidApplication.class, args);
	}

}
