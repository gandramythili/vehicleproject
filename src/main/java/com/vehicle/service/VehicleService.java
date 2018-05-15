package com.vehicle.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicle.dao.VehicleCategoryRepository;
import com.vehicle.dao.VehicleRepository;
import com.vehicle.exception.InvalidRequest;
import com.vehicle.exception.VehicleNotFoundException;
import com.vehicle.model.Response;
import com.vehicle.model.Vehicle;
import com.vehicle.model.VehicleCategory;
import com.vehicle.model.VehicleResponse;

@Service
public class VehicleService {

	@Autowired
	VehicleRepository vehicleRepository;

	@Autowired
	VehicleCategoryRepository vehicleCategoryRepository;

	public VehicleResponse getVehicleDetails(Long id) {
		Optional<Vehicle> vehicleResult = vehicleRepository.findById(id);
		if (!vehicleResult.isPresent()) {
			throw new VehicleNotFoundException("Vehicle Not Found.");
		}
		return VehicleResponse.builder()
				.vehicle(vehicleResult.get())
				.response(Response.builder()
				.code(200)
				.message("Success")
				.source("Vehicle-Inventory")
				.build())
				.build();

	}

	public Vehicle addVehicle(Vehicle vehicleRequest) {
		Long typeId = 0L;
		String vehicleType = vehicleRequest.getVehicleCategory().getType();
		switch (vehicleType) {
		case "CAR":
			typeId = 1L;
			break;
		case "AIRPLANE":
			typeId = 2L;
			break;
		case "AMPHIBIAN":
			typeId = 3L;
			break;
		case "BOAT":
			typeId = 4L;
			break;
		case "TRUCK":
			typeId = 5L;
			break;

		}
		Optional<VehicleCategory> vehicleCategory = vehicleCategoryRepository.findById(typeId);
		if (!vehicleCategory.isPresent()) {
			throw new InvalidRequest("Invalid Vehicle Type.");
		}
		Vehicle vehicle = Vehicle.builder()
				.vehicleCategory(vehicleCategory.get())
				.make(vehicleRequest.getMake())
				.model(vehicleRequest.getModel())
				.vinNumber(vehicleRequest.getVinNumber())
				.year(vehicleRequest.getYear())
				.price(vehicleRequest.getPrice())
				.createdDate(new Date())
				.build();
		return vehicleRepository.save(vehicle);

	}

	public Vehicle updateVehicle(Vehicle vehicle) {
		Optional<Vehicle> savedVehicle = vehicleRepository.findById(vehicle.getId());
		if (!savedVehicle.isPresent()) {
			throw new InvalidRequest("Invalid Update : Vehicle does not exist with the given details.");
		}
		vehicle.setCreatedDate(new Date());
		Vehicle updatedVehicle = vehicleRepository.save(vehicle);
		return updatedVehicle;
	}

	public VehicleResponse delete(Long id) {
		vehicleRepository.deleteById(id);
		return VehicleResponse.builder()
				.response(Response.builder()
				.code(200)
				.message("Success")
				.source("Vehicle-Inventory")
				.build())
				.build();
	}

}
