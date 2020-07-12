package com.binamra100.models;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
@Table(name = "owners")
public class Owner extends Person {
    @Column(name = "address")
    @NotEmpty
    private String address;

    @Column(name = "city")
    @NotEmpty
    private String city;

    @Column(name = "telephone")
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Car> cars;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    protected Set<Car> getCarsInternal() {
        if (this.cars == null) {
            this.cars = new HashSet<>();
        }
        return this.cars;
    }

    protected void setCarsInternal(Set<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        List<Car> sortedCars = new ArrayList<>(getCarsInternal());
        PropertyComparator.sort(sortedCars, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedCars);
    }

    public void addCar(Car car) {
        if (car.isNew()) {
            getCarsInternal().add(car);
        }
        car.setOwner(this);
    }

    public Car getCar(String name) {
        return getCar(name, false);
    }


    public Car getCar(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Car car : getCarsInternal()) {
            if (!ignoreNew || !car.isNew()) {
                String compName = car.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return car;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId()).append("new", this.isNew()).append("lastName", this.getLastName())
                .append("firstName", this.getFirstName()).append("address", this.address).append("city", this.city)
                .append("telephone", this.telephone).toString();
    }
}
