package com.wt.axon.common.command;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserCommand {

    @TargetAggregateIdentifier
    private Long id;

}
