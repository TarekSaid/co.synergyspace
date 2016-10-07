package co.synergyspace.businesses.steps;

import co.synergyspace.businesses.AppConfig;
import co.synergyspace.businesses.entities.impl.BusinessEntity;
import co.synergyspace.businesses.repositories.IBusinessRepository;
import cucumber.api.DataTable;
import cucumber.api.java8.En;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by tarek on 21/09/16.
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = "eureka.client.enabled:false")
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("test")
public class BusinessSteps extends AbstractTestNGSpringContextTests implements En {

    @Inject
    private IBusinessRepository<BusinessEntity> repository;

    @Inject
    private TestRestTemplate restTemplate;

    private String name;

    private String result;

    public BusinessSteps() {
        Given("^that the following businesses exist$", (DataTable dataTable) -> {
            List<BusinessEntity> businesses = dataTable.asList(BusinessEntity.class);

            List<BusinessEntity> foundBusinesses = (List<BusinessEntity>) repository.findAll();
            if (foundBusinesses.isEmpty()) {
                repository.save(businesses);
            }

            assertThat(repository.findAll()).containsAll(businesses);
        });

        Given("^that I am the business \"([^\"]*)\"$", (String name) -> this.name = name);

        When("I register my business", () -> {
            BusinessEntity be = new BusinessEntity(this.name);
            this.result = restTemplate.postForObject("/new", be, String.class);
        });

        When("^I search for the business \"([^\"]*)\"$", (String name) -> {
            this.result = restTemplate.getForObject("/{name}", String.class, name);
        });

        When("^I list all businesses$", () -> {
            this.result = restTemplate.getForObject("/", String.class);
        });


        Then("^I should see$", (String json) -> {
            assertThat(result).matches(json);
        });
    }
}
