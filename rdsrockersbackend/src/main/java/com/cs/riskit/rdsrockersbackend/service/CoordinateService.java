package com.cs.riskit.rdsrockersbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.riskit.rdsrockersbackend.dao.CoordinateDao;
import com.cs.riskit.rdsrockersbackend.model.Axis;
import com.cs.riskit.rdsrockersbackend.model.Coordinate;

@Service
public class CoordinateService {

	@Autowired
	private CoordinateDao coordinateDao;
	
	public List<Coordinate> getCoordinates(Axis a) {
		return coordinateDao.getCoordinates(a);
	}
	
	public List<String> getXColumnNames() {
		return coordinateDao.getXColumnNames();
	}
	
	public List<String> getYColumnNames() {
		return coordinateDao.getYColumnNames();
	}

}
