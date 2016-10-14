package co.synergyspace.projects.services.impl;

import co.synergyspace.projects.dataproviders.BusinessDataProvider;
import co.synergyspace.projects.entities.impl.BusinessEntity;
import co.synergyspace.projects.repositories.IBusinessRepository;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
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
}
