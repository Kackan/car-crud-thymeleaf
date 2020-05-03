package com.kackan.carcrudthymeleaf.service;

import com.kackan.carcrudthymeleaf.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

        boolean addCar(Car car);
        List<Car> getCars();
        Optional<Car> getCarById(Long id);
        void deleteCar(Long id);
        void updateCar(Car car);
        void updateOneFieldOfCar(Car car, String type, String value);
}
