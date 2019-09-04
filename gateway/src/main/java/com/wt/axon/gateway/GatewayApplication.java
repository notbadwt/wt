package com.wt.axon.gateway;

import com.wt.axon.common.command.CreateUserCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayApplication.class);

    private final CommandGateway commandGateway;

    @Autowired
    public GatewayApplication(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostConstruct
    public void init() {
        commandGateway.send(new CreateUserCommand(1L));
        LOGGER.info("send end");

    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
