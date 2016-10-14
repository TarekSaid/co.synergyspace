package co.synergyspace.projects.services.impl;

import co.synergyspace.projects.dataproviders.ProjectDataProvider;
import co.synergyspace.projects.entities.Business;
import co.synergyspace.projects.entities.impl.BusinessEntity;
import co.synergyspace.projects.entities.impl.ProjectEntity;
import co.synergyspace.projects.repositories.IProjectRepository;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import org.testng.annotations.Test;

import java.util.List;

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
}
