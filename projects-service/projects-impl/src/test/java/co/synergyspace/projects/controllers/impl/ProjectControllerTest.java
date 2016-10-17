package co.synergyspace.projects.controllers.impl;

import co.synergyspace.projects.dataproviders.BusinessDataProvider;
import co.synergyspace.projects.dataproviders.ProjectDataProvider;
import co.synergyspace.projects.entities.Business;
import co.synergyspace.projects.entities.Project;
import co.synergyspace.projects.entities.impl.BusinessEntity;
import co.synergyspace.projects.entities.impl.ProjectEntity;
import co.synergyspace.projects.exceptions.impl.BusinessNotFoundException;
import co.synergyspace.projects.exceptions.impl.ProjectNotFoundException;
import co.synergyspace.projects.exceptions.impl.ProjectNotOwnedException;
import co.synergyspace.projects.services.IBusinessService;
import co.synergyspace.projects.services.IProjectService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

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

    @Test(expectedExceptions = BusinessNotFoundException.class)
    public void getProjectShouldThrowBusinessNotFoundExceptionWhenNull() {
        new Expectations() {{
            businessService.findByName(anyString);
            result = null;
        }};

        projectController.getProject("", 1L);
    }

    @Test(dataProviderClass = BusinessDataProvider.class, dataProvider = "ownedProjects")
    public void getProjectShouldReturnProjectFound(String name, Long id, Business business, Project project) {
        new Expectations() {{
            businessService.findByName(name);
            result = business;
        }};

        assertThat(projectController.getProject(name, id)).isEqualTo(project);
    }

    @Test(expectedExceptions = ProjectNotOwnedException.class)
    public void getProjectShouldReturnProjectNotOwnedWhenNull() {
        projectController.getProject("", 1L);
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

    @Test(expectedExceptions = BusinessNotFoundException.class)
    public void involveShouldThrowBusinessNotFoundWhenNull() {
        new Expectations() {{
            businessService.findByName(anyString);
            result = null;
        }};

        projectController.involve("", 1L, null);
    }

    @Test(dataProviderClass = BusinessDataProvider.class, dataProvider = "ownedProjects")
    public void involveShouldAddBusinesses(String name, Long id, Business business, ProjectEntity project) {
        new Expectations() {{
            businessService.findByName(name);
            result = business;
        }};

        projectController.involve(name, id, null);

        new Verifications() {{
            projectService.involveBusinesses(project, (Set<BusinessEntity>) any);
        }};
    }

    @Test(expectedExceptions = ProjectNotOwnedException.class)
    public void involveShouldThrowProjectNotOwnedExceptionWhenNotFound() {
        projectController.involve("", 1L, null);
    }

    @Test(dataProviderClass = BusinessDataProvider.class, dataProvider = "ownedProjects")
    public void involveShouldReturnProject(String name, Long id, Business business, ProjectEntity project) {
        new Expectations() {{
            businessService.findByName(name);
            result = business;
            projectService.involveBusinesses(project, null);
            result = project;
        }};

        assertThat(projectController.involve(name, id, null)).isEqualTo(project);
    }
}
