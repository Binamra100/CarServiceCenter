package com.binamra100.controllers;

import com.binamra100.models.Car;
import com.binamra100.models.CarType;
import com.binamra100.models.Owner;
import com.binamra100.repositories.CarRepository;
import com.binamra100.repositories.OwnerRepository;
import com.binamra100.validators.CarValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class CarController {
    private static final String VIEWS_CARS_CREATE_OR_UPDATE_FORM = "cars/createOrUpdateCarForm";

    private final CarRepository carRepository;

    private final OwnerRepository ownerRepository;

    public CarController(CarRepository argCarRepository, OwnerRepository argOwnerRepository) {
        this.carRepository = argCarRepository;
        this.ownerRepository = argOwnerRepository;
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") int ownerId) {
        return this.ownerRepository.findById(ownerId).get();
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder("car")
    public void initCarBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new CarValidator());
    }

    @ModelAttribute("types")
    public Collection<CarType> populatePetTypes() {
        return this.carRepository.findCarTypes();
    }

    @GetMapping("/cars/new")
    public String initCreationForm(Owner owner, ModelMap model) {
        Car car = new Car();
        owner.addCar(car);
        model.put("car", car);
        return VIEWS_CARS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/cars/new")
    public String processCreationForm(Owner owner, @Valid Car car, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(car.getName()) && car.isNew() && owner.getCar(car.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.addCar(car);
        if (result.hasErrors()) {
            model.put("car", car);
            return VIEWS_CARS_CREATE_OR_UPDATE_FORM;
        } else {
            this.carRepository.save(car);
            return "redirect:/owners/{ownerId}";
        }
    }
}
