package com.binamra100.repositories;

import com.binamra100.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    @Query("SELECT DISTINCT owner FROM Owner owner left join fetch owner.cars WHERE owner.lastName LIKE :lastName%")
    @Transactional(readOnly = true)
    List<Owner> findByLastName(String lastName);
}
