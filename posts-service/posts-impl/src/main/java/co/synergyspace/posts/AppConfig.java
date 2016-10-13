package co.synergyspace.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by tarek on 26/09/16.
 */
@EnableTransactionManagement
@EnableNeo4jRepositories({"co.synergyspace.posts.repositories", "BOOT-INF.classes.co.synergyspace.posts.repositories"})
@EntityScan({"co.synergyspace.posts.entities", "BOOT-INF.classes.co.synergyspace.posts.entities"})
@SpringBootApplication
@EnableEurekaClient
public class AppConfig {
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }
}
