package com.binamra100.models;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "Cars")
public class Car extends NamedEntity {

    @Column(name = "manufacture_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate manufacturedDate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private CarType type;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Transient
    private Set<Visit> visits = new LinkedHashSet<>();

    public Owner getOwner() {
        return this.owner;
    }

    protected void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getManufacturedDate() {
        return manufacturedDate;
    }

    public void setManufacturedDate(LocalDate manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    protected Set<Visit> getVisitsInternal() {
        if (this.visits == null) {
            this.visits = new HashSet<>();
        }
        return this.visits;
    }

    public void setVisitsInternal(Collection<Visit> visits) {
        this.visits = new LinkedHashSet<>(visits);
    }

    public List<Visit> getVisits() {
        List<Visit> sortedVisits = new ArrayList<>(getVisitsInternal());
        PropertyComparator.sort(sortedVisits, new MutableSortDefinition("visitDate", false, false));
        return Collections.unmodifiableList(sortedVisits);
    }

    public void addVisit(Visit visit) {
        getVisitsInternal().add(visit);
        visit.setCarId(this.getId());
    }
}
