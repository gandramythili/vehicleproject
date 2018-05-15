package com.vehicle.dao;

import org.springframework.data.repository.CrudRepository;

import com.vehicle.model.VehicleCategory;

public interface VehicleCategoryRepository extends CrudRepository<VehicleCategory, Long> {
}
