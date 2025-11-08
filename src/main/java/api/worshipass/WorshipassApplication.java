package api.worshipass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("api.worshipass.domain")
public class WorshipassApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorshipassApplication.class, args);
	}

}
