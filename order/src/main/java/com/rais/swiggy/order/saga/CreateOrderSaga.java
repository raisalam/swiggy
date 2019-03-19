package com.rais.swiggy.order.saga;

import com.rais.swiggy.common.command.ReserveCreditCommand;
import com.rais.swiggy.common.command.ValidateOrderByConsumer;
import com.rais.swiggy.common.domain.Money;
import com.rais.swiggy.order.api.reply.ApproveOrderCommand;
import com.rais.swiggy.order.api.reply.RejectOrderCommand;
import com.rais.swiggy.order.sagaparticipants.ConsumerServiceProxy;
import io.eventuate.tram.commands.consumer.CommandWithDestination;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;

import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;

public class CreateOrderSaga implements SimpleSaga<CreateOrderSagaData> {

  private SagaDefinition<CreateOrderSagaData> sagaDefinition;

  public CreateOrderSaga(ConsumerServiceProxy consumerService) {

    this.sagaDefinition =
            step()
              .withCompensation(this::reject)
            .step()
              .invokeParticipant(consumerService.validateOrder, this ::makeValidateOrderByConsumerCommand )
            .step()
              .invokeParticipant(consumerService.reserveCredit, this ::reserveCreditCommand )
            .step()
              .invokeParticipant(this::approve)
            .build();

  }

  @Override
  public SagaDefinition<CreateOrderSagaData> getSagaDefinition() {
    return this.sagaDefinition;
  }


  private CommandWithDestination reserveCredit(CreateOrderSagaData data) {
    long orderId = data.getOrderId();
    Long customerId = data.getOrderDetails().getCustomerId();
    Money orderTotal = data.getOrderDetails().getOrderTotal();
    return send(new ReserveCreditCommand(customerId, orderId, orderTotal))
            .to("customerService")
            .build();
  }

  public CommandWithDestination reject(CreateOrderSagaData data) {
    System.out.println("CreateOrderSaga :: reject() Going to reject order");
    return send(new RejectOrderCommand(data.getOrderId()))
            .to("orderService")
            .build();
  }

  private CommandWithDestination approve(CreateOrderSagaData data) {
    System.out.println("CreateOrderSaga :: approve() Going to approve order");

    return send(new ApproveOrderCommand(data.getOrderId()))
            .to("orderService")
            .build();
  }

  ValidateOrderByConsumer makeValidateOrderByConsumerCommand(CreateOrderSagaData data) {
    return new ValidateOrderByConsumer(data.getOrderDetails().getCustomerId(), data.getOrderId(), data.getOrderDetails().getOrderTotal());
  }

  ReserveCreditCommand reserveCreditCommand(CreateOrderSagaData data) {
    return new ReserveCreditCommand(data.getOrderDetails().getCustomerId(), data.getOrderId(), data.getOrderDetails().getOrderTotal());
  }
}
