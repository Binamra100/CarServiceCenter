package com.binamra100.formatters;

import com.binamra100.models.CarType;
import com.binamra100.repositories.CarRepository;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class CarTypeFormatter implements Formatter<CarType> {

    CarRepository carRepository;

    public CarTypeFormatter(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @Override
    public CarType parse(String text, Locale locale) throws ParseException {
        Collection<CarType> findPetTypes = this.carRepository.findCarTypes();
        for (CarType type : findPetTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }
        throw new ParseException("type not found: " + text, 0);
    }

    @Override
    public String print(CarType carType, Locale locale) {
        return carType.getName();
    }
}
