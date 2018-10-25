package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.java.ServiceScan;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableJpaRepositories
@ServiceScan
@RestController
@EnableBinding(VehicleSource.class)
public class ManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}
	
	@Autowired
	ManagementRepository repo;
	
	@Autowired
	VehicleSource source;
	
	@GetMapping("/")
	public List<Vehicle> getVehicles(){
		return repo.findAll();
	}
	
	@PostMapping("/vehicle")
	public Vehicle updateVehicle(@RequestBody Vehicle vehicle) {
		Vehicle v = repo.save(vehicle);
		if(v.getStatus().equals(Status.maintenance)) {
			source.channel().send(MessageBuilder.withPayload(vehicle).build());
		}
		return v;
	}
}
