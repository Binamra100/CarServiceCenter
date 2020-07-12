package com.binamra100.validators;

import com.binamra100.models.Car;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CarValidator implements Validator {

    private static final String REQUIRED = "required";

    @Override
    public boolean supports(Class<?> aClass) {
        return Car.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Car car = (Car) obj;
        String name = car.getName();
        // name validation
        if (!StringUtils.hasLength(name)) {
            errors.rejectValue("name", REQUIRED, REQUIRED);
        }

        // type validation
        if (car.isNew() && car.getType() == null) {
            errors.rejectValue("type", REQUIRED, REQUIRED);
        }

        // manufacture date validation
        if (car.getManufacturedDate() == null) {
            errors.rejectValue("manufacturedDate", REQUIRED, REQUIRED);
        }
    }
}
