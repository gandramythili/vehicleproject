package com.vehicle.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.vehicle.exception.InvalidRequest;
import com.vehicle.model.Vehicle;


@Component
public class Validator {

	public void validateVehicleRequest(Vehicle vehicleRequest) {
		if (!(StringUtils.hasText(vehicleRequest.getVehicleCategory().getType()))) {
			throw new InvalidRequest("Vehicle Type is Required");
		}

	}

	public void validateGetRequest(Long id) {

	}

}
