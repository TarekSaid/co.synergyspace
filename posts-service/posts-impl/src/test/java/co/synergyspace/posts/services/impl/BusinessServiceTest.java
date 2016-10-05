package co.synergyspace.posts.services.impl;

import co.synergyspace.posts.dataproviders.BusinessDataProvider;
import co.synergyspace.posts.entities.Business;
import co.synergyspace.posts.entities.Post;
import co.synergyspace.posts.entities.impl.BusinessEntity;
import co.synergyspace.posts.entities.impl.PostEntity;
import co.synergyspace.posts.repositories.IBusinessRepository;
import mockit.*;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tarek on 03/10/16.
 */
@Test
public class BusinessServiceTest {

    @Tested
    private BusinessService businessService;

    @Injectable
    private IBusinessRepository<BusinessEntity> businessRepository;

    public void businessServiceShouldCallRepository() {
        businessService.findByName("");

        new Verifications() {{
            businessRepository.findByName(anyString);
        }};
    }

    @Test(dataProvider = "businesses", dataProviderClass = BusinessDataProvider.class)
    public void businessServiceShouldReturnFoundBusiness(String name, Business business) {
        new Expectations() {{
            businessRepository.findByName(name);
            result = business;
        }};

        assertThat(businessService.findByName(name)).isEqualTo(business);
    }

    public void addPostShouldWriteThePost(@Mocked BusinessEntity business) {
        businessService.addPost(business, new PostEntity());

        new Verifications() {{
            business.write((Post) any);
        }};
    }

    public void addPostShouldSaveBusiness(@Mocked BusinessEntity business) {
        businessService.addPost(business, new PostEntity());

        new Verifications() {{
            businessRepository.save(business);
        }};
    }

    @Test(dataProvider = "savedBusinesses", dataProviderClass = BusinessDataProvider.class)
    public void addPostShouldReturnSavedPost(BusinessEntity business, BusinessEntity savedBusiness) {
        new Expectations() {{
            businessRepository.save(business);
            result = savedBusiness;
        }};

        assertThat(businessService.addPost(business, new PostEntity())).isEqualTo(savedBusiness);
    }
}
