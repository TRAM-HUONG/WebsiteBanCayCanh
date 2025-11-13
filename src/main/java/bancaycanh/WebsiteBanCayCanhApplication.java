package bancaycanh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "controller" })
@EnableJpaRepositories(basePackages = { "repository" })
@EntityScan(basePackages = { "model" })
public class WebsiteBanCayCanhApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebsiteBanCayCanhApplication.class, args);
    }
}
