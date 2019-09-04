package com.wt.axon.common.config;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.distributed.AnnotationRoutingStrategy;
import org.axonframework.commandhandling.distributed.CommandBusConnector;
import org.axonframework.commandhandling.distributed.CommandRouter;
import org.axonframework.commandhandling.distributed.DistributedCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.extensions.springcloud.commandhandling.SpringCloudCommandRouter;
import org.axonframework.extensions.springcloud.commandhandling.SpringHttpCommandBusConnector;
import org.axonframework.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestOperations;

@Configuration
public class AxonConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AxonConfig.class);


    // Example function providing a Spring Cloud Connector
    @Bean
    public CommandRouter springCloudCommandRouter(DiscoveryClient discoveryClient, Registration localServiceInstance) {
        return SpringCloudCommandRouter.builder()
                .discoveryClient(discoveryClient)
                .routingStrategy(new AnnotationRoutingStrategy())
                .localServiceInstance(localServiceInstance)
                .build();
    }

    @Bean
    public CommandBusConnector springHttpCommandBusConnector(
            @Qualifier("localSegment") CommandBus localSegment,
            RestOperations restOperations,
            Serializer serializer) {
        return SpringHttpCommandBusConnector.builder()
                .localCommandBus(localSegment)
                .restOperations(restOperations)
                .serializer(serializer)
                .build();
    }

    @Primary // to make sure this CommandBus implementation is used for autowiring
    @Bean
    public DistributedCommandBus springCloudDistributedCommandBus(
            CommandRouter commandRouter,
            CommandBusConnector commandBusConnector) {
        LOGGER.info("init DistributedCommandBus");
        return DistributedCommandBus.builder()
                .commandRouter(commandRouter)
                .connector(commandBusConnector)
                .build();
    }


    @Bean
    public CommandGateway commandGateway(CommandBus commandBus) {
        LOGGER.info("init CommandGateway");
        return DefaultCommandGateway.builder().commandBus(commandBus)
                .build();
    }


}
