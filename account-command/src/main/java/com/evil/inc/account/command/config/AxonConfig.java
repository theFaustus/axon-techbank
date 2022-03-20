package com.evil.inc.account.command.config;

import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoFactory;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoSettingsFactory;
import org.axonframework.monitoring.MessageMonitor;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class AxonConfig {

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;
    @Value("${spring.data.mongodb.port}")
    private int mongoPort;

    @Bean
    public MongoClient mongoClient() {
        final MongoFactory mongoFactory = new MongoFactory();
        final MongoSettingsFactory mongoSettingsFactory = new MongoSettingsFactory();
        mongoSettingsFactory.setMongoAddresses(Collections.singletonList(new ServerAddress(mongoHost, mongoPort)));
        mongoFactory.setMongoClientSettings(mongoSettingsFactory.createMongoClientSettings());
        return mongoFactory.createMongo();
    }

    @Bean
    public Serializer eventSerializer() {
        return JacksonSerializer.defaultSerializer();
    }

    @Bean
    public EventStorageEngine eventStorageEngine(MongoClient mongoClient) {
        return MongoEventStorageEngine.builder()
                .eventSerializer(eventSerializer())
                .snapshotSerializer(eventSerializer())
                .mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(mongoClient).build())
                .build();
    }

    @Bean
    public EmbeddedEventStore eventStore(EventStorageEngine eventStorageEngine, AxonConfiguration configuration){
        final MessageMonitor<? super EventMessage<?>> eventStore = configuration.messageMonitor(EventStore.class, "eventStore");
        return EmbeddedEventStore.builder()
                .storageEngine(eventStorageEngine)
                .messageMonitor(eventStore)
                .build();
    }
}
