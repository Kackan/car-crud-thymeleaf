package com.kackan.carcrudthymeleaf.service;

import model.Car;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CarService {

        Car addCar(Car car);
        List<Car> getCars();
        Optional<Car> getCarById(Long id);
        void deleteCar(Long id);
        Car updateCar(Car car);
        Car updateOneFieldOfCar(Car car, String type, String value);
}
