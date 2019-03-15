package com.rais.swiggy.customer.service;

import com.rais.swiggy.common.command.ReserveCreditCommand;
import com.rais.swiggy.common.replies.CustomerCreditReservationFailed;
import com.rais.swiggy.common.replies.CustomerCreditReserved;
import com.rais.swiggy.customer.domain.Customer;
import com.rais.swiggy.customer.domain.CustomerCreditLimitExceededException;
import com.rais.swiggy.customer.domain.CustomerRepository;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.*;

public class CustomerCommandHandler {

  @Autowired
  private CustomerRepository customerRepository;

  public CommandHandlers commandHandlerDefinitions() {
    return SagaCommandHandlersBuilder
            .fromChannel("customerService")
            .onMessage(ReserveCreditCommand.class, this::reserveCredit)
            .build();
  }

  public Message reserveCredit(CommandMessage<ReserveCreditCommand> cm) {
    System.out.println("CustomerCommandHandler :: reserveCredit()");
    ReserveCreditCommand cmd = cm.getCommand();
    long customerId = cmd.getCustomerId();
    Customer customer = customerRepository.findById(customerId).get();
    // TODO null check
    try {
      customer.reserveCredit(cmd.getOrderId(), cmd.getOrderTotal());
      return withSuccess(new CustomerCreditReserved());
    } catch (CustomerCreditLimitExceededException e) {
      return withFailure(new CustomerCreditReservationFailed());
    }
  }

  // withLock(Customer.class, customerId).
  // TODO @Validate to trigger validation and error reply


}
