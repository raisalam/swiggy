package com.rais.swiggy.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.rais.swiggy.view.consumers.CustomerServiceMessagingConfiguration;
import com.rais.swiggy.view.consumers.OrderHistoryServiceMessagingConfiguration;

import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;
import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;

@SpringBootApplication
@Import({ OrderHistoryServiceMessagingConfiguration.class, CustomerServiceMessagingConfiguration.class,
		ViewConfiguration.class, TramJdbcKafkaConfiguration.class })
public class ViewApplication {

	@Bean
	public ChannelMapping channelMapping() {
		return DefaultChannelMapping.builder().build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ViewApplication.class, args);
	}

}
