package co.synergyspace.businesses.steps;

import co.synergyspace.businesses.config.AppTestConfig;
import co.synergyspace.businesses.config.PersistenceTestConfig;
import co.synergyspace.businesses.config.WebTestConfig;
import co.synergyspace.businesses.entities.impl.BusinessEntity;
import co.synergyspace.businesses.repositories.impl.BusinessRepository;
import cucumber.api.DataTable;
import cucumber.api.java8.En;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by tarek on 21/09/16.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {AppTestConfig.class, PersistenceTestConfig.class, WebTestConfig.class})
@Transactional
public class BusinessStepDefs extends AbstractTestNGSpringContextTests implements En {

    @Inject
    private BusinessRepository repository;

    private final RestTemplate restTemplate;

    private String name;

    private String result;

    public BusinessStepDefs() {
        restTemplate = new RestTemplate();

        Given("^that the following businesses exist$", (DataTable dataTable) -> {
            List<BusinessEntity> businesses = new ArrayList<>();

            Map<Long, String> map = dataTable.asMap(Long.class, String.class);
            for (Map.Entry<Long, String> entry : map.entrySet()) {
                BusinessEntity b = new BusinessEntity();

                b.setId(entry.getKey());
                b.setName(entry.getValue());

                businesses.add(b);
            }

            repository.save(businesses);

            assertThat(repository.findAll()).containsAll(businesses);
        });

        Given("^that I am the business \"([^\"]*)\"$", (String name) -> {
            this.name = name;
        });

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
