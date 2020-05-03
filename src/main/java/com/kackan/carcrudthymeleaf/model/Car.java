package com.kackan.carcrudthymeleaf.model;


import javax.validation.constraints.NotBlank;

public class Car {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String color;

    public Car(){}

    public Car(Long id,String name, String color) {
        this.id=id;
        this.name = name;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

}
