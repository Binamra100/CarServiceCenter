package com.binamra100.controllers;


import com.binamra100.models.Car;
import com.binamra100.models.Visit;
import com.binamra100.repositories.CarRepository;
import com.binamra100.repositories.VisitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class VisitController {

    private final VisitRepository visitRepository;

    private final CarRepository carRepository;

    private static final String VIEWS_VISIT_CREATE_OR_UPDATE_FORM = "cars/createOrUpdateVisitForm";

    public VisitController(VisitRepository argVisitRepository, CarRepository argCarRepository) {
        this.visitRepository = argVisitRepository;
        this.carRepository = argCarRepository;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    public Visit loadCarWithVisit(@PathVariable("carId") int carId, Map<String, Object> model) {
        Car car = this.carRepository.findById(carId).get();
        car.setVisitsInternal(this.visitRepository.findByCarId(carId));
        model.put("car", car);
        Visit visit = new Visit();
        car.addVisit(visit);
        return visit;
    }

    @GetMapping("/owners/*/cars/{carId}/visits/new")
    public String initNewVisitForm(@PathVariable("carId") int carId, Map<String, Object> model) {
        return VIEWS_VISIT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/owners/{ownerId}/cars/{carId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_VISIT_CREATE_OR_UPDATE_FORM;
        } else {
            this.visitRepository.save(visit);
            return "redirect:/owners/{ownerId}";
        }
    }
}
