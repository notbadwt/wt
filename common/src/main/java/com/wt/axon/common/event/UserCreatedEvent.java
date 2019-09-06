package com.wt.axon.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author tao.wang
 */
@Data
@AllArgsConstructor
public class UserCreatedEvent {

    @TargetAggregateIdentifier
    private Long id;

}
