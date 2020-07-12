package com.binamra100.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Table(name = "visits")
public class Visit  extends BaseEntity{

    @Column(name= "car_id")
    private Integer carId;

    @Column(name= "visit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate visitDate;

    @NotEmpty
    @Column(name= "description")
    private String description;

    public Visit() {
        this.visitDate = LocalDate.now();
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
