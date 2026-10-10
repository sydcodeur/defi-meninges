package fr.didiersenou.defimeningesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class DefiMeningesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DefiMeningesApiApplication.class, args);
	}

}
