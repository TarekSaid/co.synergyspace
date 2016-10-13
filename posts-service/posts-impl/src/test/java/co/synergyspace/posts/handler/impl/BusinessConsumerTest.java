package co.synergyspace.posts.handler.impl;

import co.synergyspace.posts.consumers.impl.BusinessConsumer;
import co.synergyspace.posts.entities.impl.BusinessEntity;
import co.synergyspace.posts.repositories.IBusinessRepository;
import mockit.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tarek on 10/10/16.
 */
@Test
public class BusinessConsumerTest {

    @Tested
    private BusinessConsumer businessConsumer;

    @Injectable
    private IBusinessRepository<BusinessEntity> businessRepository;

    @DataProvider(name = "business-names")
    public Iterator<Object[]> createNames() {
        Collection<Object[]> params = new ArrayList<>();

        params.add(new Object[]{""});
        params.add(new Object[]{"test"});
        params.add(new Object[]{"Tarek"});

        return params.iterator();
    }

    @Test(dataProvider = "business-names")
    public void businessCreatedShouldSaveBusinessWithTheNameReceived(String name) {
        businessConsumer.businessCreated(name);

        new Verifications() {{
            BusinessEntity businessEntity = new BusinessEntity(name);
            businessRepository.save(businessEntity);
        }};
    }
}
