package pl.edu.amu.dji.jms.lab10.books;

import com.google.common.collect.Lists;
import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import pl.edu.amu.dji.jms.lab10.books.model.Book;
import pl.edu.amu.dji.jms.lab10.books.repository.BookRepository;

import java.util.List;
import java.util.logging.Filter;

@ComponentScan
@EnableAutoConfiguration
@RestController
public class BookConfiguration {

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
        SpringApplication.run(BookConfiguration.class, args);
    }
}
