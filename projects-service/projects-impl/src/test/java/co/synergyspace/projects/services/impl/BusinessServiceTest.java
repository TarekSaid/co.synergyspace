package co.synergyspace.projects.services.impl;

import co.synergyspace.projects.dataproviders.BusinessDataProvider;
import co.synergyspace.projects.dataproviders.ProjectDataProvider;
import co.synergyspace.projects.entities.Business;
import co.synergyspace.projects.entities.Project;
import co.synergyspace.projects.entities.impl.BusinessEntity;
import co.synergyspace.projects.entities.impl.ProjectEntity;
import co.synergyspace.projects.repositories.IBusinessRepository;
import mockit.*;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tarek on 14/10/16.
 */
@Test
public class BusinessServiceTest {

    @Tested
    private BusinessService businessService;

    @Injectable
    private IBusinessRepository<BusinessEntity> businessRepository;

    public void findByNameShouldCallBusinessRepository() {
        businessService.findByName("");

        new Verifications() {{
            businessRepository.findByName(anyString);
        }};
    }

    @Test(dataProviderClass = BusinessDataProvider.class, dataProvider = "businesses")
    public void findByNameShouldReturnTheBusinessFound(String name, BusinessEntity business) {
        new Expectations() {{
            businessRepository.findByName(name);
            result = business;
        }};

        assertThat(businessService.findByName(name)).isEqualTo(business);
    }

    public void addProjectShouldAddProjectToBusiness(@Mocked BusinessEntity business, @Mocked ProjectEntity project) {
        businessService.addProject(business, project);

        new Verifications() {{
            business.create(project);
        }};
    }

    public void addProjectShouldCallProjectRepository() {
        businessService.addProject(new BusinessEntity(), new ProjectEntity());

        new Verifications() {{
            businessRepository.save((BusinessEntity) any);
        }};
    }

    @Test(dataProviderClass = BusinessDataProvider.class, dataProvider = "savedBusinesses")
    public void addProjectShouldSaveBusiness(BusinessEntity business, BusinessEntity savedBusiness) {
        new Expectations() {{
            businessRepository.save(business);
            result = savedBusiness;
        }};

        assertThat(businessService.addProject(business, new ProjectEntity())).isEqualTo(savedBusiness);
    }
}
