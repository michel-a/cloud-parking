package com.malves.parking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malves.parking.controller.dto.ParkingDTO;
import com.malves.parking.controller.mapper.ParkingMapper;
import com.malves.parking.model.Parking;
import com.malves.parking.service.ParkingService;

@RestController
@RequestMapping(value = "/parkings")
public class ParkingController {
	
	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;
	
	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.parkingService = parkingService;
		this.parkingMapper = parkingMapper;
	}

	@GetMapping
	public List<ParkingDTO> findAll() {
		List<Parking> parkingList = parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		return result;
	}
}
