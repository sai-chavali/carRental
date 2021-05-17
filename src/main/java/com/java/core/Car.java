package com.java.core;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Car {

    @JsonProperty
    private Integer id;

    @NotNull
    @JsonProperty
    private String name;

    public Car() {
        // Jackson deserialization
    }

    @NotNull
    @JsonProperty
    private String model;

    @NotNull
    @JsonProperty
    private Integer made;

    @NotNull
    @JsonProperty
    private String color;

    @NotNull
    @JsonProperty
    private BigDecimal price;


    @JsonProperty
    private CarAvailabilityStatus status;

    public Car(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Car(@NotNull Integer id, @NotNull String name, @NotNull String model, @NotNull Integer made, @NotNull String color, @NotNull BigDecimal price, @NotNull CarAvailabilityStatus status) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.made = made;
        this.color = color;
        this.price = price;
        this.status = status;
    }

    public Car(@NotNull String name, @NotNull String model, @NotNull Integer made, @NotNull String color, @NotNull BigDecimal price) {
        this.name = name;
        this.model = model;
        this.made = made;
        this.color = color;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMade() {
        return made;
    }

    public void setMade(Integer made) {
        this.made = made;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CarAvailabilityStatus getStatus() {
        if(status == null)
            return CarAvailabilityStatus.AVAILABLE;
        return status;
    }

    public void setStatus(CarAvailabilityStatus status) {
        this.status = status;
    }
}

