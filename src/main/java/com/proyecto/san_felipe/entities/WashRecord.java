package com.proyecto.san_felipe.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class WashRecord {

    @Id

    private String id;
    private Employee employee;
    private Car car;
    private ServiceOffered providedService;

    public WashRecord(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ServiceOffered getProvidedService() {
        return providedService;
    }

    public void setProvidedService(ServiceOffered providedService) {
        this.providedService = providedService;
    }
}
