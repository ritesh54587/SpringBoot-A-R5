package com.sangamone.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sangamone.model.Train;
import com.sangamone.repo.TrainRepo;

@RestController
public class TrainContoller {

	@Autowired
	TrainRepo repo;
	
	
	@GetMapping("/listOfTrain")
	public Iterable<Train> listOFTrain() {
		Iterable<Train>  list= new ArrayList<>();
		list= repo.findAll();
		return list;
		
		
		
	}
}
