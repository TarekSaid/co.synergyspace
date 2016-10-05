package co.synergyspace.businesses.services.impl;

import co.synergyspace.businesses.dataproviders.BusinessDataProvider;
import co.synergyspace.businesses.entities.Business;
import co.synergyspace.businesses.entities.impl.BusinessEntity;
import co.synergyspace.businesses.repositories.IBusinessRepository;
import co.synergyspace.businesses.repositories.impl.BusinessRepository;
import mockit.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests the Business Service Implementation.
 * Created by tarek on 12/09/16.
 */
@Test
public class BusinessServiceTest {

    /**
     * the tested Business service
     **/
    @Tested
    private BusinessService businessService;

    /**
     * the mocked Business repository
     **/
    @Injectable
    private IBusinessRepository<BusinessEntity> businessRepository;

    @Mocked
    private BusinessEntity business;

    /**
     * Verifies if the service is calling the correct repository method.
     */
    public void findAllShouldCallFindAllFromRepository() {
        businessService.findAll();

        new Verifications() {{
            businessRepository.findAll();
        }};
    }

    /**
     * Verifies if the service is returning the businesses correctly.
     *
     * @param businesses the list of businesses created by the BusinessDataProvider.
     */
    @Test(dataProvider = "businesses", dataProviderClass = BusinessDataProvider.class)
    public void findAllShouldReturnListOfBusinesses(final List<Business> businesses) {
        new Expectations() {{
            businessRepository.findAll();
            result = businesses;
        }};

        assertThat(businessService.findAll()).isEqualTo(businesses);
    }

    /**
     * Verifies if the service calls the correct repository method.
     */
    public void findByNameShouldCallFindByNameFromRepository() {
        businessService.findByName("");

        new Verifications() {{
            businessRepository.findByName(anyString);
        }};
    }

    /**
     * Verifies if the service is returning the correct Business.
     *
     * @param name     business name created by the BusinessDataProvider.
     * @param business the expected Business, created by the BusinessDataProvider.
     */
    @Test(dataProvider = "business", dataProviderClass = BusinessDataProvider.class)
    public void findByNameShouldReturnFoundBusiness(final String name, final Business business) {
        new Expectations() {{
            businessRepository.findByName(name);
            result = business;
        }};

        assertThat(businessService.findByName(name)).isEqualTo(business);
    }

    public void findByNameShouldReturnNullWhenNotFound() {
        new Expectations() {{
            businessRepository.findByName(anyString);
            result = new EmptyResultDataAccessException(1);
        }};

        assertThat(businessService.findByName("")).isNull();
    }

    public void addBusinessShouldCallSaveFromRepository() {
        businessService.addBusiness(business);

        new Verifications() {{
            businessRepository.save(business);
        }};
    }

    @Test(dataProvider = "added-businesses", dataProviderClass = BusinessDataProvider.class)
    public void addBusinessShouldReturnSavedBusiness(final BusinessEntity business, final BusinessEntity savedBusiness) {
        new Expectations() {{
            businessRepository.save(business);
            result = savedBusiness;
        }};

        assertThat(businessService.addBusiness(business)).isEqualTo(savedBusiness);
    }

    @Test
    public void addBusinessShouldReturnNullWhenNameIsDuplicated() {
        new Expectations() {{
            businessRepository.save(business);
            result = new DataIntegrityViolationException("");
        }};

        assertThat(businessService.addBusiness(business)).isNull();
    }
}
