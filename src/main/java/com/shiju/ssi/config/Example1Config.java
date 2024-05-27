package com.shiju.ssi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;

@Configuration
public class Example1Config {

	@Bean(name = "request-in-channel")
	MessageChannel requestInChannel() {
		return MessageChannels.direct().getObject();
	}

	@Bean(name = "order-process-channel")
	MessageChannel orderProcessChannel() {		
		return MessageChannels.queue(10).getObject();
	}

	@Bean(name = "order-reply-channel")
	MessageChannel orderReplyChannel() {
		return MessageChannels.queue(10).getObject();
	}
}
