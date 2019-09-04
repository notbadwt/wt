package com.wt.axon.client.aggregate;


import com.wt.axon.common.command.CreateUserCommand;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@NoArgsConstructor
@Aggregate(repository = "userRepository")
public class User {

    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    @AggregateIdentifier
    private Long id;

    @CommandHandler
    public User(CreateUserCommand createUserCommand) {
        LOGGER.info("Create User Command id:{}", createUserCommand.getId());
    }

}
