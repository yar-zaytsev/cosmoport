package com.space.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;
//import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



import javax.validation.constraints.*;

/**
 * Created by Ярпиво on 10.05.2019.
 */

@Entity
//@Validated
@Table(name = "ship")
public class Ship implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id", length = 6, nullable = false)
    private long id;

    @Column(name = "name")
    @NotEmpty(message = "name not be empty")
    @Size(max = 50, message = "Name must be less  50")
    private String name;


    @Column(name = "planet")
    @NotEmpty(message = "planet not be empty")
    @Size(max = 50, message = "Planet must be less 50")
    private String planet;


    @Column(name = "shipType")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "ShipType not be null")
    private ShipType shipType;

    @Column(name = "prodDate")
    @NotNull
    private Date prodDate;

    @Column(name = "isUsed")
    private Boolean isUsed;

    @Column(name = "speed")
    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("0.99")
    private Double speed;

    @Column(name = "crewSize")
    @NotNull
    @Min(1) @Max(9999)
    private Integer crewSize;

    @Column(name = "rating")
    private Double rating;

    protected Ship() {
    }


    public Ship(String name, String planet, ShipType shipType, Date prodDate, boolean isUsed, double speed, int crewSize, double rating) {
        this.name = name;
        this.planet = planet;
        this.shipType = shipType;
        this.prodDate = prodDate;
        this.isUsed = isUsed;
        this.speed = speed;
        this.crewSize = crewSize;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public Boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = new BigDecimal(speed).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public int getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(int crewSize) {
        this.crewSize = crewSize;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

