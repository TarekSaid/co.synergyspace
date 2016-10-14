package co.synergyspace.projects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by tarek on 14/10/16.
 */
@EnableTransactionManagement
@EnableNeo4jRepositories({"co.synergyspace.projects.repositories", "BOOT-INF.classes.co.synergyspace.projects.repositories"})
@EntityScan({"co.synergyspace.projects.entities", "BOOT-INF.classes.co.synergyspace.projects.entities"})
@SpringBootApplication
@EnableEurekaClient
public class ProjectsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectsApplication.class, args);
    }
}
