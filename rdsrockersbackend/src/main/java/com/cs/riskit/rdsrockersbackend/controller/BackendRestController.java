package com.cs.riskit.rdsrockersbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.riskit.rdsrockersbackend.model.Axis;
import com.cs.riskit.rdsrockersbackend.model.Coordinate;
import com.cs.riskit.rdsrockersbackend.service.CoordinateService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BackendRestController {
	
	@Autowired
	private CoordinateService coordinateService;

	@RequestMapping("/lol")
	public String greeting() {
		return "lol";
	}
	
	@RequestMapping("/getcoordinates")
	public List<Coordinate> getCoordinates(@RequestBody Axis a){
		return coordinateService.getCoordinates(a);
	}
	
	@RequestMapping("/getxcolumnnames")
	public List<String> getColumnNames(){
		return coordinateService.getXColumnNames();
	}
	
	@RequestMapping("/getycolumnnames")
	public List<String> getYColumnNames(){
		return coordinateService.getYColumnNames();
	}
}
