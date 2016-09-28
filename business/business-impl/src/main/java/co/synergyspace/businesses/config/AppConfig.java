package co.synergyspace.businesses.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by tarek on 12/09/16.
 */
@Configuration
@Import({PersistenceContext.class, WebContext.class})
@ComponentScan("co.synergyspace.businesses")
public class AppConfig {
}
