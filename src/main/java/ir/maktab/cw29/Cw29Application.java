package ir.maktab.cw29;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Cw29Application {

    public static void main(String[] args) {
        SpringApplication.run(Cw29Application.class, args);
    }

}
