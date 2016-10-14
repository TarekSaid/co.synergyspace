package co.synergyspace.projects.controllers.impl;

import co.synergyspace.projects.dataproviders.BusinessDataProvider;
import co.synergyspace.projects.dataproviders.ProjectDataProvider;
import co.synergyspace.projects.entities.Business;
import co.synergyspace.projects.entities.impl.BusinessEntity;
import co.synergyspace.projects.entities.impl.ProjectEntity;
import co.synergyspace.projects.exceptions.impl.BusinessNotFoundException;
import co.synergyspace.projects.services.IBusinessService;
import co.synergyspace.projects.services.IProjectService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by tarek on 14/10/16.
 */
@Test
public class ProjectControllerTest {

    @Tested
    private ProjectController projectController;

    @Injectable
    private IBusinessService<BusinessEntity> businessService;

    @Injectable
    private IProjectService<ProjectEntity> projectService;

    public void listProjectsFromShouldCallBusinessService() {
        projectController.listProjectsFrom("");

        new Verifications() {{
            businessService.findByName(anyString);
        }};
    }

    @Test(expectedExceptions = BusinessNotFoundException.class)
    public void listProjectsFromShouldThrowExceptionWhenNameNotFound() {
        new Expectations() {{
            businessService.findByName(anyString);
            result = null;
        }};

        projectController.listProjectsFrom("");
    }

    @Test(dataProvider = "businesses", dataProviderClass = BusinessDataProvider.class)
    public void listProjectsFromShouldCallProjectService(String name, BusinessEntity business) {
        new Expectations() {{
            businessService.findByName(name);
            result = business;
        }};

        projectController.listProjectsFrom(name);

        new Verifications() {{
            projectService.findProjectsFrom(business);
        }};
    }

    @Test(dataProvider = "projects", dataProviderClass = ProjectDataProvider.class)
    public void listProjectsFromShouldReturnProjectsFound(List<ProjectEntity> projects) {
        new Expectations() {{
            projectService.findProjectsFrom((Business) any);
            result = projects;
        }};

        assertThat(projectController.listProjectsFrom("")).isEqualTo(projects);
    }
}
