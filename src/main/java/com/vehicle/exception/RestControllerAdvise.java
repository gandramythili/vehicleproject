package com.vehicle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vehicle.model.Response;
import com.vehicle.model.VehicleResponse;

@RestControllerAdvice
public class RestControllerAdvise {

	@ExceptionHandler({ VehicleNotFoundException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public VehicleResponse vehicleNotFound(VehicleNotFoundException vehicleNotFoundException) {
		return VehicleResponse.builder()
				.response(Response.builder()
				.code(404)
				.message("Vehicle Not Found")
				.source("Vehicle-Inventory")
				.build())
				.build();
	}

	@ExceptionHandler({ InvalidRequest.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public VehicleResponse invalidRequest(InvalidRequest invalidRequest) {

		return VehicleResponse.builder()
				.response(Response.builder()
				.message(invalidRequest.getMessage())
				.code(400)
				.message("Invalid Request")
				.source("Vehicle-Inventory")
				.build())
				.build();
	}

	@ExceptionHandler({ RuntimeException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public VehicleResponse runtimeException(RuntimeException runtimeException) {
		return VehicleResponse.builder()
				.response(Response.builder()
				.message("Internal Server Error")
				.code(500)
				.source("Vehicle-Inventory")
				.build())
				.build();
	}

}
