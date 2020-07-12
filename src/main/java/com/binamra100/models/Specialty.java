package com.binamra100.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "specialties")
public class Specialty extends NamedEntity implements Serializable {
}
