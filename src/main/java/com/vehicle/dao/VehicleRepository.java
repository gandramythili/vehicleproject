package com.vehicle.dao;

import org.springframework.data.repository.CrudRepository;

import com.vehicle.model.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
}
