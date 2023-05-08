package com.example.hardware_softwareshopping.model;

import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@XmlRootElement(name="owner")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class Raport {

    @XmlAttribute(name="list-of-cars")
    private Date currentData;

    @XmlElement(name = "numbers")
    private int noUsers;
}
