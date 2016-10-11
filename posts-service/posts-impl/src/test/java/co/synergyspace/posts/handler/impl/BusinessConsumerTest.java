package co.synergyspace.posts.handler.impl;

import co.synergyspace.posts.entities.impl.BusinessEntity;
import co.synergyspace.posts.consumers.impl.BusinessConsumer;
import co.synergyspace.posts.repositories.IBusinessRepository;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;
import org.testng.annotations.Test;

/**
 * Created by tarek on 10/10/16.
 */
@Test
public class BusinessConsumerTest {

    @Tested
    private BusinessConsumer businessConsumer;

    @Injectable
    private IBusinessRepository<BusinessEntity> businessRepository;

    public void businessReceivedShouldCallBusinessRepository(@Mocked BusinessEntity business) {
        businessConsumer.businessReceived(business);

        new Verifications() {{
            businessRepository.save(business);
        }};
    }
}
