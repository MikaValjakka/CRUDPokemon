package xyz.mikavee.CRUDDEmo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class CruddEmoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddEmoApplication.class, args);
	}

}
