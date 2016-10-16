package co.synergyspace.projects.services.impl;

import co.synergyspace.projects.dataproviders.ProjectDataProvider;
import co.synergyspace.projects.entities.Business;
import co.synergyspace.projects.entities.impl.BusinessEntity;
import co.synergyspace.projects.entities.impl.ProjectEntity;
import co.synergyspace.projects.repositories.IProjectRepository;
import mockit.*;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tarek on 14/10/16.
 */
@Test
public class ProjectServiceTest {

    @Tested
    private ProjectService projectService;

    @Injectable
    private IProjectRepository<ProjectEntity> projectRepository;

    public void findProjectsFromShouldCallProjectRepository() {
        projectService.findProjectsFrom(null);

        new Verifications() {{
            projectRepository.findByBusiness((Business) any);
        }};
    }

    @Test(dataProvider = "businessProjects", dataProviderClass = ProjectDataProvider.class)
    public void findProjectsFromShouldReturnProjectsFound(BusinessEntity business, List<ProjectEntity> projects) {
        new Expectations() {{
            projectRepository.findByBusiness(business);
            result = projects;
        }};

        assertThat(projectService.findProjectsFrom(business)).isEqualTo(projects);
    }

    public void findProjectsByIdShouldCallProjectRepository() {
        projectService.findProjectById(1L);

        new Verifications() {{
            projectRepository.findOne(1L);
        }};
    }

    @Test(dataProviderClass = ProjectDataProvider.class, dataProvider = "projectById")
    public void findProjectsByIdShouldReturnTheProjectFound(Long id, ProjectEntity project) {
        new Expectations() {{
            projectRepository.findOne(id);
            result = project;
        }};

        assertThat(projectService.findProjectById(id)).isEqualTo(project);
    }

    public void addBusinessesShouldInvolveBusinesses(@Mocked ProjectEntity project) {
        projectService.involveBusinesses(project, null);

        new Verifications() {{
            project.involve((Set<BusinessEntity>) any);
        }};
    }

    public void addBusinessesShouldSaveTheProject() {
        projectService.involveBusinesses(new ProjectEntity(), new HashSet<>());

        new Verifications() {{
            projectRepository.save((ProjectEntity) any);
        }};
    }

    @Test(dataProviderClass = ProjectDataProvider.class, dataProvider = "savedProjects")
    public void addBusinessesShouldReturnSavedProject(ProjectEntity project, ProjectEntity savedProject) {
        new Expectations() {{
            projectRepository.save(project);
            result = savedProject;
        }};

        assertThat(projectService.involveBusinesses(project, new HashSet<>())).isEqualTo(savedProject);
    }
}
