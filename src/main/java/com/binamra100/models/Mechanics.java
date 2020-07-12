package com.binamra100.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Mechanics {

    private List<Mechanic> mechanics;

    @XmlElement
    public List<Mechanic> getMechanicList() {
        if (mechanics == null) {
            mechanics = new ArrayList<>();
        }
        return mechanics;
    }
}
