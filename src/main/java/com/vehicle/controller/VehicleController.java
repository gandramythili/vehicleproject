package com.vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.model.Vehicle;
import com.vehicle.model.VehicleResponse;
import com.vehicle.service.VehicleService;
import com.vehicle.validator.Validator;

@RestController
@EnableAutoConfiguration
public class VehicleController {

	@Autowired
	VehicleService vehicleService;

	@Autowired
	Validator validator;

	@GetMapping("/vechicle/search")
	public VehicleResponse seachVechicleById(@RequestHeader(name = "id") Long id) {
		VehicleResponse vehicleResponse = vehicleService.getVehicleDetails(id);
		return vehicleResponse;
	}

	@PostMapping("/vechicle/add")
	public Vehicle createVechicle(@RequestBody Vehicle vehicleRequest) {
		validator.validateVehicleRequest(vehicleRequest);
		return vehicleService.addVehicle(vehicleRequest);
	}

	@PutMapping("/vechicle/modify")
	public Vehicle updateVehicle(@RequestBody Vehicle vehicleRequest) {
		validator.validateVehicleRequest(vehicleRequest);
		return vehicleService.updateVehicle(vehicleRequest);
	}

	@DeleteMapping("/vechicle/delete")
	public VehicleResponse deleteVehicleById(@RequestHeader(name = "id") Long id) {
		return vehicleService.delete(id);
	}
}
