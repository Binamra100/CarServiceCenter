package com.binamra100.repositories;

import com.binamra100.models.Car;
import com.binamra100.models.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query("SELECT ctype FROM CarType ctype ORDER BY ctype.name")
    @Transactional(readOnly = true)
    List<CarType> findCarTypes();
}
