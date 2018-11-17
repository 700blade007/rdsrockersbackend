package com.cs.riskit.rdsrockersbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.riskit.rdsrockersbackend.model.Coordinate;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BackendRestController {

	@RequestMapping("/lol")
	public String greeting() {
		return "lol";
	}
	
	@RequestMapping("/getcoordinates")
	public List<Coordinate> getCoordinates(){
		List<Coordinate> coordinateList = new ArrayList<Coordinate>();
		coordinateList.add(new Coordinate("arijit", 80));
		coordinateList.add(new Coordinate("bob", 70));
		coordinateList.add(new Coordinate("alice", 90));
		coordinateList.add(new Coordinate("ali", 100));
		return coordinateList;
	}
}
