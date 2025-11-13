package bancaycanh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "controller" })
@EnableJpaRepositories(basePackages = { "repository" })
@EntityScan(basePackages = { "model" })
public class WebsiteBanCayCanhApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebsiteBanCayCanhApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebsiteBanCayCanhApplication.class, args);
    }
}
