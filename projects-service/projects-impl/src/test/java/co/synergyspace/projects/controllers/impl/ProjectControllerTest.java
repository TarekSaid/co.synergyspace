package co.synergyspace.projects.controllers.impl;

import co.synergyspace.projects.dataproviders.BusinessDataProvider;
import co.synergyspace.projects.dataproviders.ProjectDataProvider;
import co.synergyspace.projects.entities.Business;
import co.synergyspace.projects.entities.Project;
import co.synergyspace.projects.entities.impl.BusinessEntity;
import co.synergyspace.projects.entities.impl.ProjectEntity;
import co.synergyspace.projects.exceptions.impl.BusinessNotFoundException;
import co.synergyspace.projects.exceptions.impl.ProjectNotFoundException;
import co.synergyspace.projects.services.IBusinessService;
import co.synergyspace.projects.services.IProjectService;
import javafx.scene.web.PromptData;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

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
    private IProjectService<ProjectEntity, BusinessEntity> projectService;

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

    public void getProjectShouldCallProjectService() {
        projectController.getProject(1L);

        new Verifications() {{
            projectService.findProjectById(1L);
        }};
    }

    @Test(dataProviderClass = ProjectDataProvider.class, dataProvider = "projectById")
    public void getProjectShouldReturnProjectFound(Long id, ProjectEntity project) {
        new Expectations() {{
            projectService.findProjectById(id);
            result = project;
        }};

        assertThat(projectController.getProject(id)).isEqualTo(project);
    }

    @Test(expectedExceptions = ProjectNotFoundException.class)
    public void getProjectShouldThrowProjectNotFoundWhenNull() {
        new Expectations() {{
            projectService.findProjectById(anyLong);
            result = null;
        }};

        projectController.getProject(1L);
    }

    public void createProjectShouldFindBusiness() {
        projectController.createProject("", new ProjectEntity());

        new Verifications() {{
            businessService.findByName(anyString);
        }};
    }

    @Test(expectedExceptions = BusinessNotFoundException.class)
    public void createProjectShouldReturnBusinessNotFoundWhenNull() {
        new Expectations() {{
            businessService.findByName(anyString);
            result = null;
        }};

        projectController.createProject("", new ProjectEntity());
    }

    @Test(dataProviderClass = BusinessDataProvider.class, dataProvider = "businesses")
    public void createProjectShouldCallBusinessService(String name, BusinessEntity business) {
        new Expectations() {{
            businessService.findByName(name);
            result = business;
        }};

        projectController.createProject(name, new ProjectEntity());

        new Verifications() {{
            businessService.addProject(business, (ProjectEntity) any);
        }};
    }

    @Test(dataProviderClass = ProjectDataProvider.class, dataProvider = "addProjects")
    public void createProjectShouldUseProject(ProjectEntity project, BusinessEntity business) {
        new Expectations() {{
            businessService.addProject((BusinessEntity) any, project);
            result = business;
        }};

        assertThat(projectController.createProject("", project)).isEqualTo(business);
    }

    public void involveShouldCallProjectService() {
        projectController.involve(1L, null);

        new Verifications() {{
            projectService.findProjectById(1L);
        }};
    }

    @Test(dataProviderClass = ProjectDataProvider.class, dataProvider = "projectById")
    public void involveShouldAddBusinesses(Long id, ProjectEntity project) {
        new Expectations() {{
            projectService.findProjectById(id);
            result = project;
        }};

        projectController.involve(id, null);

        new Verifications() {{
            projectService.involveBusinesses(project, (Set<BusinessEntity>) any);
        }};
    }
}
