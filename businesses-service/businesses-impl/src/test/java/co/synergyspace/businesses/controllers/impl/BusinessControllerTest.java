package co.synergyspace.businesses.controllers.impl;

import co.synergyspace.businesses.dataproviders.BusinessDataProvider;
import co.synergyspace.businesses.entities.Business;
import co.synergyspace.businesses.entities.impl.BusinessEntity;
import co.synergyspace.businesses.exceptions.impl.BusinessExistsException;
import co.synergyspace.businesses.exceptions.impl.BusinessNotFoundException;
import co.synergyspace.businesses.services.IBusinessService;
import mockit.*;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by tarek on 12/09/16.
 */
@Test
public class BusinessControllerTest {
    @Tested
    private BusinessController businessController;

    @Injectable
    private IBusinessService<BusinessEntity> businessService;

    public void listBusinessesShouldCallFindAllFromService() {
        businessController.listBusinesses();

        new Verifications() {{
            businessService.findAll();
        }};
    }

    @Test(dataProvider = "businesses", dataProviderClass = BusinessDataProvider.class)
    public void listBusinessesShouldReturnListOfBusinesses(final List<Business> businesses) {
        new Expectations() {{
            businessService.findAll();
            result = businesses;
        }};

        assertThat(businessController.listBusinesses()).isEqualTo(businesses);
    }

    public void getBusinessShouldCallFindByNameFromService() {
        businessController.getBusiness("");

        new Verifications() {{
            businessService.findByName(anyString);
        }};
    }

    @Test(dataProvider = "business", dataProviderClass = BusinessDataProvider.class)
    public void getBusinessShouldReturnFoundBusiness(final String name, final Business business) {
        new Expectations() {{
            businessService.findByName(name);
            result = business;
        }};

        assertThat(businessController.getBusiness(name)).isEqualTo(business);
    }

    @Test(expectedExceptions = BusinessNotFoundException.class)
    public void getBusinessShouldThrowExceptionWhenNull() {
        new Expectations() {{
            businessService.findByName(anyString);
            result = null;
        }};

        businessController.getBusiness("");
    }

    public void addBusinessShouldCallSaveFromService(final @Mocked BusinessEntity be) {
        businessController.addBusiness(be);

        new Verifications() {{
            businessService.addBusiness(be);
        }};
    }

    @Test(dataProvider = "added-businesses", dataProviderClass = BusinessDataProvider.class)
    public void addBusinessShouldReturnTheSavedBusiness(final BusinessEntity business, final Business savedBusiness) {
        new Expectations() {{
            businessService.addBusiness(business);
            result = savedBusiness;
        }};

        assertThat(businessController.addBusiness(business)).isEqualTo(savedBusiness);
    }

    @Test(expectedExceptions = BusinessExistsException.class)
    public void addBusinessShouldThrowBusinessExistsException(final @Mocked BusinessEntity business) {
        new Expectations() {{
            businessService.addBusiness(business); result = null;
        }};

        businessController.addBusiness(business);
    }
}
