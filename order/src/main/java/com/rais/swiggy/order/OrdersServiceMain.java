package com.rais.swiggy.order;

import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;
import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;
import io.eventuate.tram.commands.producer.TramCommandProducerConfiguration;
import io.eventuate.tram.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.sagas.orchestration.SagaOrchestratorConfiguration;
import io.eventuate.tram.sagas.participant.SagaParticipantConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Configuration
@Import({OrderConfiguration.class,
        TramEventsPublisherConfiguration.class,
        TramCommandProducerConfiguration.class,
        SagaOrchestratorConfiguration.class,
        TramJdbcKafkaConfiguration.class,
        SagaParticipantConfiguration.class})
public class OrdersServiceMain {

  @Bean
  public ChannelMapping channelMapping() {
    return DefaultChannelMapping.builder().build();
  }

  public static void main(String[] args) {
    SpringApplication.run(OrdersServiceMain.class, args);
  }

}
