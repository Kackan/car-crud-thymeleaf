package com.kackan.carcrudthymeleaf.controller;
import com.kackan.carcrudthymeleaf.service.CarService;
import model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class CarController {

    CarService service;

    @Autowired
    public CarController(CarService service) {
        this.service = service;
    }


    @GetMapping("/cars")
    public String getAll(Model model)
    {
        List <Car> cars=service.getCars();
        model.addAttribute("cars",cars);
        model.addAttribute("newCar",new Car());
        return "main-view";
    }

    @GetMapping("/cars/edit/{id}")
    public String updateCarForm(@PathVariable Long id, Model model)
    {
        Optional<Car> carById = service.getCarById(id);
            if(carById.isPresent())
            {
                model.addAttribute("car",carById.get());
                return "car-update-form";
            }else{
                return "redirect:/cars";
            }
    }

    @PostMapping("/cars/save/{id}")
    public String saveUpdatedCar(@PathVariable("id") Long id, @Valid Car car, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "car-update-form";
        }

        service.updateCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/cars/delete/{id}")
    public String deleteCar(@PathVariable Long id)
    {
        Optional<Car>carToRemove=service.getCarById(id);

        if(carToRemove.isPresent())
        {
            service.deleteCar(id);
        }
        return "redirect:/cars";
    }

    @PostMapping("/cars/add-car")
    public String addCar(@Valid @ModelAttribute Car car, BindingResult result)
    {

        if(result.hasErrors())
        {
            return "redirect:/cars";
        }

        Car car1 = service.addCar(car);
        if(car1!=null)
        {
            return "redirect:/cars";
        }else
        {
            return "errorId";
        }
    }


    @GetMapping("/cars/update-one-field/{id}/{type}/{value}")
    public String updateOneFieldOfCar(@PathVariable("id") Long id, @PathVariable("type")String type, @PathVariable("value") String value)
    {
        System.out.println("Id: "+id);
        System.out.println("Type: "+type);
        System.out.println("Value: "+value);


        Optional<Car> car=service.getCarById(id);
        car.ifPresent(car1 -> service.updateOneFieldOfCar(car1, type, value));
        return "redirect:/cars";
    }

}
