@SpringBootApplication
@ComponentScan(basePackages = {"controller"})
@EnableJpaRepositories(basePackages = {"repository"})
@EntityScan(basePackages = {"model"})
public class WebsiteBanCayCanhApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebsiteBanCayCanhApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebsiteBanCayCanhApplication.class, args);
    }
}
