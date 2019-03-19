package com.rais.swiggy.order.sagaparticipants;

import com.rais.swiggy.common.command.ReserveCreditCommand;
import com.rais.swiggy.common.command.ValidateOrderByConsumer;
import io.eventuate.tram.commands.common.Success;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;

public class ConsumerServiceProxy {


  public final CommandEndpoint<ValidateOrderByConsumer> validateOrder= CommandEndpointBuilder
          .forCommand(ValidateOrderByConsumer.class)
          .withChannel("customerService")
          .withReply(Success.class)
          .build();

  public final CommandEndpoint<ReserveCreditCommand> reserveCredit= CommandEndpointBuilder
          .forCommand(ReserveCreditCommand.class)
          .withChannel("customerService")
          .withReply(Success.class)
          .build();

}
