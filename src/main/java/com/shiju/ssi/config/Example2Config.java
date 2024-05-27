package com.shiju.ssi.config;


import java.time.Instant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.GenericHandler;
import org.springframework.integration.core.GenericSelector;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;

import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class Example2Config {
	
	@Bean
	MessageChannel aTob() {
		return MessageChannels.direct().getObject();
	}
	
	
	
	private String text() {
		return Math.random() > .5 ? "Hello World "+Instant.now()+ "!" : "hola todo "+Instant.now()+ "!";
	}
	
	@Bean
	IntegrationFlow flow() {
		return IntegrationFlow.from(new MessageSource<String>() {

			@Override
			public Message<String> receive() {
				return MessageBuilder.withPayload(text() ).build();
			}
			
		}, poller -> poller.poller(pm -> pm.fixedRate(100))).channel(aTob())
				.get();
	}
	
	@Bean
	IntegrationFlow flow1() {
		return IntegrationFlow
				.from(aTob())
				.filter(String.class, source ->  source.contains("hola"))
				.transform(new GenericTransformer<String, String>() {
					@Override
					public String transform(String source) {
						
						return source.toUpperCase();
					}
				})
				.handle(new GenericHandler<String>() {
					@Override
					public Object handle(String payload, MessageHeaders headers) {
						log.info("The payload is {}",payload);
						return null;
					}
					
				})
				.get();
	}

}
