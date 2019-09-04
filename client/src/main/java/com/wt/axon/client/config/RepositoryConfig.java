package com.wt.axon.client.config;

import com.wt.axon.client.aggregate.User;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean(name = "userRepository")
    public Repository<User> repositoryForUser(EventStore eventStore) {
        return EventSourcingRepository.builder(User.class)
                .eventStore(eventStore)
                .build();
    }

}
