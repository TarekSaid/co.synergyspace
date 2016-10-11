package co.synergyspace.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * Created by tarek on 26/09/16.
 */
@SpringBootApplication
@EnableNeo4jRepositories("co.synergyspace.posts.repositories")
@EnableEurekaClient
@EnableBinding(Sink.class)
public class AppConfig {
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }
}
