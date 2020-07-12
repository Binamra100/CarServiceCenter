package com.binamra100.controllers;

import com.binamra100.models.Mechanics;
import com.binamra100.repositories.MechanicRespository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class MechanicController {

    private final MechanicRespository mechanicRespository;

    public MechanicController(MechanicRespository argMechanicRespository) {
        this.mechanicRespository = argMechanicRespository;
    }

    @GetMapping("/mechanics.html")
    public String showVetList(Map<String, Object> model) {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet
        // objects so it is simpler for Object-Xml mapping
        Mechanics mechanics = new Mechanics();
        mechanics.getMechanicList().addAll(this.mechanicRespository.findAll());
        model.put("mechanics", mechanics);
        return "mechanics/mechanicList";
    }

    @GetMapping({ "/mechanics" })
    public @ResponseBody
    Mechanics showResourcesVetList() {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet
        // objects so it is simpler for JSon/Object mapping
        Mechanics mechanics = new Mechanics();
        mechanics.getMechanicList().addAll(this.mechanicRespository.findAll());
        return mechanics;
    }
}
