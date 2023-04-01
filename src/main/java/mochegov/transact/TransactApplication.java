package mochegov.transact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class TransactApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactApplication.class, args);
	}
}
