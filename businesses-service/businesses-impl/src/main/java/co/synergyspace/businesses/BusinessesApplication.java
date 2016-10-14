package co.synergyspace.businesses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by tarek on 12/09/16.
 */
@SpringBootApplication
@EnableEurekaClient
public class BusinessesApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessesApplication.class, args);
    }
}