package my.project.ttn_3.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/addcustomer").setViewName("addcustomer");
        registry.addViewController("/updatecustomer").setViewName("updatecustomer");
        registry.addViewController("/addttn").setViewName("addttn");
        registry.addViewController("/submitOrder").setViewName("submitOrder");

    }

}
