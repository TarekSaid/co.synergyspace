package co.synergyspace.businesses.producers.impl;

import mockit.*;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by tarek on 13/10/16.
 */
@Test
public class BusinessProducerTest {

    @Tested
    private BusinessProducer businessProducer;

    @Injectable
    private Source source;

    public void sendShouldBuildMessageWithName(@Mocked MessageBuilder msgBuilder) {
        businessProducer.send("Test");

        new Verifications() {{
            source.output().send(msgBuilder.withPayload("Test").build());
        }};
    }
}
