package com.kackan.carcrudthymeleaf.service;

import com.kackan.carcrudthymeleaf.model.Car;
import com.kackan.carcrudthymeleaf.model.TypeOfField;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private List<Car>cars;

    public CarServiceImpl()
    {
        cars=new ArrayList<>();
        cars.add(new Car(1L,"BMW","RED"));
        cars.add(new Car(2L,"MERCEDES","BLACK"));
        cars.add(new Car(3L,"FORD","WHITE"));
    }

    @Override
    public boolean addCar(Car car) {

        List<Long>ids=new ArrayList<>();
        cars.forEach(c-> ids.add(c.getId()));

        if(ids.contains(car.getId()))
        {
            return false;
        }
        else
        {
            cars.add(car);
            return true;
        }
    }

    @Override
    public List<Car> getCars() {
        return cars;
    }

    @Override
    public Optional<Car> getCarById(Long id) {

        return cars.stream().filter(c -> c.getId().equals(id)).findFirst();

    }

    @Override
    public void deleteCar(Long id) {
        cars.stream().filter(c->c.getId().equals(id)).findFirst().ifPresent(c->cars.remove(c));
    }

    @Override
    public void updateCar(Car car) {
        Car carToUpdate = cars.stream().filter(c -> c.getId().equals(car.getId())).findFirst().get();
        carToUpdate.setColor(car.getColor());
        carToUpdate.setName(car.getName());
        }


    @Override
    public void updateOneFieldOfCar(Car car, String type, String value) {

            if(type.equalsIgnoreCase(TypeOfField.NAME.name()))
            {
                car.setName(value);
            }
            else if(type.equalsIgnoreCase(TypeOfField.COLOR.name()))
            {
                car.setColor(value);
            }else {
                System.out.println("Field doesn't exist");
            }
    }
}
