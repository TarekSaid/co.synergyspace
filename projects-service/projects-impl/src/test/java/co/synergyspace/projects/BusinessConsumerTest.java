package co.synergyspace.projects;

import co.synergyspace.projects.consumers.impl.BusinessConsumer;
import co.synergyspace.projects.entities.impl.BusinessEntity;
import co.synergyspace.projects.repositories.IBusinessRepository;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by tarek on 17/10/16.
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
    public void businessReceivedShouldSaveTheBusinessReceived(String name) {
        businessConsumer.businessReceived(name);

        new Verifications() {{
            BusinessEntity business = new BusinessEntity(name);
            businessRepository.save(business);
        }};
    }
}
