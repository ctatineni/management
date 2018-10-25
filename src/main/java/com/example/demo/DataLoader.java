package com.example.demo;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
	
	@Autowired
	ManagementRepository repo;
	
	
	@PostConstruct
	public void loadData() {
		repo.deleteAll();
		Vehicle v1 = new Vehicle("Ford","Fiesta","2011",Status.available);
		save(v1);
		Vehicle v2 = new Vehicle("Nissan","Altima","2014",Status.available);
		save(v2);
		Vehicle v3 = new Vehicle("Dodge","Ram","2016",Status.available);
		save(v3);
		Vehicle v4 = new Vehicle("Chryslre","Pacifica","2013",Status.available);
		save(v4);
		Vehicle v5 = new Vehicle("Tayota","Camary","2017",Status.available);
		save(v5);
		Vehicle v6 = new Vehicle("Nissan","Rouge","2018",Status.available);
		save(v6);
//		Flux.just(new Vehicle("Econamy","Ford Fiesta or similar",30,"automatic"),
//				new Vehicle("Compact","Ford Focus or similar",40,"automatic"),
//				new Vehicle("Intermediate","Chevrolet Cruze or similar",50,"automatic"))
//				.flatMap(repo::save)
//		        .subscribe(System.out::println);
		
	}
	
	private void save(Vehicle v) {
//		UUID id = UUID.randomUUID();
//		v.setId(id.toString());
		UUID vin = UUID.randomUUID();
		v.setVin(vin.toString());
//		UUID mva = UUID.randomUUID();
//		v.setMva(mva.toString());
		repo.save(v);
	}
	

}
