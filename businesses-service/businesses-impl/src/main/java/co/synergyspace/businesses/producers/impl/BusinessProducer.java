package co.synergyspace.businesses.producers.impl;

import co.synergyspace.businesses.producers.IBusinessProducer;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by tarek on 13/10/16.
 */
@Component
@EnableBinding(Source.class)
public class BusinessProducer implements IBusinessProducer {

    @Inject
    private Source source;

    @Override
    public void send(String name) {
        source.output().send(MessageBuilder.withPayload(name).build());
    }
}
