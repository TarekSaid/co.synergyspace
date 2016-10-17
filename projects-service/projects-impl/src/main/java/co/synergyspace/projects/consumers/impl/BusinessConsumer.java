package co.synergyspace.projects.consumers.impl;

import co.synergyspace.projects.consumers.IBusinessConsumer;
import co.synergyspace.projects.entities.impl.BusinessEntity;
import co.synergyspace.projects.repositories.IBusinessRepository;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import javax.inject.Inject;

/**
 * Created by tarek on 17/10/16.
 */
@EnableBinding(Sink.class)
public class BusinessConsumer implements IBusinessConsumer {

    @Inject
    private IBusinessRepository<BusinessEntity> businessRepository;

    @Override
    @StreamListener(Sink.INPUT)
    public void businessReceived(String name) {
        businessRepository.save(new BusinessEntity(name));
    }
}
