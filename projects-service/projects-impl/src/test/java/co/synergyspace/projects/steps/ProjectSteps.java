package co.synergyspace.projects.steps;

import co.synergyspace.projects.ProjectsApplication;
import co.synergyspace.projects.entities.Project;
import co.synergyspace.projects.entities.impl.BusinessEntity;
import co.synergyspace.projects.entities.impl.ProjectEntity;
import co.synergyspace.projects.repositories.IBusinessRepository;
import co.synergyspace.projects.repositories.IProjectRepository;
import cucumber.api.DataTable;
import cucumber.api.java8.En;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by tarek on 14/10/16.
 */
@SpringBootTest(webEnvironment = RANDOM_PORT, properties = "spring.cloud.config.enabled:false")
@ContextConfiguration(classes = ProjectsApplication.class)
@ActiveProfiles("test")
public class ProjectSteps extends AbstractTestNGSpringContextTests implements En {

    @Inject
    private IBusinessRepository<BusinessEntity> businessRepository;

    @Inject
    private IProjectRepository<ProjectEntity> projectRepository;

    @Inject
    private TestRestTemplate restTemplate;

    private BusinessEntity business;

    private ProjectEntity project;

    private String result;

    public ProjectSteps() {
        Given("^that I have the business \"([^\"]*)\"$", (String name) -> {
            businessRepository.save(new BusinessEntity(name));
            business = businessRepository.findByName(name);
            assertThat(business.getName()).isEqualTo(name);
        });

        Given("^that I have the following project(s)?:$", (String plural, DataTable dataTable) -> {
            List<ProjectEntity> projects = dataTable.asList(ProjectEntity.class);

            projects.forEach(p -> business.create(p));
            businessRepository.save(business);
            List<ProjectEntity> projectList = (List<ProjectEntity>) projectRepository.findByBusiness(business);
            assertThat(projectList).isNotEmpty();

            if (plural == null) {
                project = projectList.stream().findFirst().get();
            }
        });

        When("^I list my projects$", () -> {
            this.result = restTemplate.getForObject("/{name}/projects", String.class, business.getName());
        });

        When("^I add the project:$", (DataTable table) -> {
            List<ProjectEntity> projects = table.asList(ProjectEntity.class);
            this.result = restTemplate.postForObject("/{name}/projects/new", projects.get(0), String.class, business.getName());
        });

        When("^I involve the following businesses:$", (DataTable table) -> {
            List<BusinessEntity> bList = new ArrayList<>();
            table.asList(String.class).forEach(n -> bList.add(new BusinessEntity(n)));

            Iterable<BusinessEntity> businesses = businessRepository.save(bList);
            this.result = restTemplate.postForObject("/{name}/projects/{id}/involve", businesses,
                    String.class, business.getName(), project.getId());
        });

        When("^I search for that project$", () -> {
            this.result = restTemplate.getForObject("/{name}/projects/{id}", String.class, business.getName(), project.getId());
        });

        Then("^I should see$", (String json) -> {
            assertThat(result).matches(json);
        });
    }
}
