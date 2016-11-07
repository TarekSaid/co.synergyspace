package co.synergyspace.accounts.services.impl;

import co.synergyspace.accounts.entities.impl.BusinessEntity;
import co.synergyspace.accounts.providers.BusinessProvider;
import co.synergyspace.accounts.repositories.IBusinessRepository;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by tarek on 07/11/16.
 */
@Test
public class BusinessServiceTest {

    @Tested
    private BusinessService businessService;

    @Injectable
    private IBusinessRepository<BusinessEntity> businessRepository;

    public void findByNameShouldCallRepository() {
        businessService.findByName("");

        new Verifications() {{
            businessRepository.findByName(anyString);
        }};
    }

    @Test(dataProviderClass = BusinessProvider.class, dataProvider = "businesses")
    public void findByNameShouldReturnFoundBusiness(String name, BusinessEntity business) {
        new Expectations() {{
            businessRepository.findByName(name);
            result = business;
        }};

        assertThat(businessService.findByName(name)).isEqualTo(business);
    }
}
