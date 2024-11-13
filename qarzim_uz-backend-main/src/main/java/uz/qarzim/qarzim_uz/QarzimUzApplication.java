package uz.qarzim.qarzim_uz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class QarzimUzApplication {

    public static void main(String[] args) {
        SpringApplication.run(QarzimUzApplication.class, args);
    }

}
