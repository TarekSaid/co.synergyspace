package co.synergyspace.posts.consumers.impl;

import co.synergyspace.posts.entities.impl.BusinessEntity;
import co.synergyspace.posts.consumers.IBusinessConsumer;
import co.synergyspace.posts.repositories.IBusinessRepository;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import javax.inject.Inject;

/**
 * Created by tarek on 10/10/16.
 */
@EnableBinding(Sink.class)
public class BusinessConsumer implements IBusinessConsumer<BusinessEntity> {

    @Inject
    private IBusinessRepository<BusinessEntity> businessRepository;

    @Override
    @StreamListener(Sink.INPUT)
    public void businessReceived(BusinessEntity business) {
        businessRepository.save(business);
    }
}
