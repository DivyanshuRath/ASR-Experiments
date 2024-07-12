package dropwizard.springboot.dropwizard.to.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class DropwizardToSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DropwizardToSpringbootApplication.class, args);
	}

}
