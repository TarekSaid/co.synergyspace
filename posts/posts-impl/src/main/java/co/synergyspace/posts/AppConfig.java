package co.synergyspace.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * Created by tarek on 26/09/16.
 */
@SpringBootApplication
@EnableNeo4jRepositories("co.synergyspace.posts.repositories")
public class AppConfig {
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }
}
