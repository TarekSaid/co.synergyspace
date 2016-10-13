package co.synergyspace.posts.consumers.impl;

import co.synergyspace.posts.consumers.IBusinessConsumer;
import co.synergyspace.posts.entities.impl.BusinessEntity;
import co.synergyspace.posts.repositories.IBusinessRepository;
import org.neo4j.cypher.internal.compiler.v2_2.functions.Sin;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

import javax.inject.Inject;

/**
 * Created by tarek on 10/10/16.
 */
@EnableBinding(Sink.class)
public class BusinessConsumer implements IBusinessConsumer {

    @Inject
    private IBusinessRepository<BusinessEntity> businessRepository;

    @StreamListener(Sink.INPUT)
    public void businessCreated(String name) {
        BusinessEntity business = new BusinessEntity(name);
        businessRepository.save(business);
    }
}
