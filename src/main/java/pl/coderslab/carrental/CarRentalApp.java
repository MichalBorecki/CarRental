package pl.coderslab.carrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class CarRentalApp {

    public static void main(String[] args) {
        SpringApplication.run(CarRentalApp.class, args);
    }
}
