package co.synergyspace.businesses.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by tarek on 21/09/16.
 */
@Configuration
@Import({PersistenceTestConfig.class, WebTestConfig.class})
@ComponentScan({"co.synergyspace.businesses","co.synergyspace.businesses.services.impl"})
public class AppTestConfig {
}
