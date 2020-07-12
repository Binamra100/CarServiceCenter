package com.binamra100.repositories;

import com.binamra100.models.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanicRespository extends JpaRepository<Mechanic, Integer> {
}
