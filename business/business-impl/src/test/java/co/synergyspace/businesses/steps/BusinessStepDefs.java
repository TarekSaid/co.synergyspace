package co.synergyspace.businesses.steps;

import co.synergyspace.businesses.config.AppConfig;
import co.synergyspace.businesses.entities.impl.BusinessEntity;
import co.synergyspace.businesses.repositories.impl.BusinessRepository;
import cucumber.api.DataTable;
import cucumber.api.java8.En;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by tarek on 21/09/16.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
public class BusinessStepDefs extends AbstractTestNGSpringContextTests implements En {

    @Inject
    private BusinessRepository repository;

    private RestTemplate restTemplate = new RestTemplate();

    private String name;

    private String result;

    public BusinessStepDefs() {
        restTemplate = new RestTemplate();

        Given("^that the following businesses exist$", (DataTable dataTable) -> {
            List<BusinessEntity> businesses = dataTable.asList(BusinessEntity.class);

            List<BusinessEntity> foundBusinesses = (List<BusinessEntity>) repository.findAll();
            if (foundBusinesses.isEmpty()) {
                repository.save(businesses);
            }

            assertThat(repository.findAll()).containsExactlyElementsOf(businesses);
        });

        Given("^that I am the business \"([^\"]*)\"$", (String name) -> this.name = name);

        When("I register my business", () -> {
            BusinessEntity be = new BusinessEntity(this.name);
            this.result = restTemplate.postForObject("http://localhost:58080/business/new", be, String.class);
        });

        When("^I search for the business \"([^\"]*)\"$", (String name) -> {
            this.result = restTemplate.getForObject("http://localhost:58080/business/{name}", String.class, name);
        });

        When("^I list all businesses$", () -> {
            this.result = restTemplate.getForObject("http://localhost:58080/businesses", String.class);
        });


        Then("^I should see$", (String json) -> {
            assertThat(result).isEqualTo(json);
        });
    }
}
