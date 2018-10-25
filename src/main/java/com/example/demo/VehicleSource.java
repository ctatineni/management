package com.example.demo;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface VehicleSource {
	
	String OUTPUT = "vechilesendchannel";
	
	@Output(OUTPUT)
	MessageChannel channel();

}
