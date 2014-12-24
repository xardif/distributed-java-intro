package pl.edu.amu.dji.jms.lab10.reviews;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.ShallowEtagHeaderFilter;


@ComponentScan
@EnableAutoConfiguration
@RestController
public class ReviewConfiguration {

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }

    @Bean
    public ShallowEtagHeaderFilter filter() {
        return new ShallowEtagHeaderFilter();
    }


    public static void main(String[] args) {
        SpringApplication.run(ReviewConfiguration.class, args);
    }
}
