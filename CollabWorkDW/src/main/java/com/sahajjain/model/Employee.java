package com.sahajjain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
    private long id;
    private String name;
    private long salary;

    public Employee() {

    }

    public Employee(long id, String name, long salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public long getSalary() {
        return salary;
    }


    @JsonProperty
    public void setSalary(long salary) {
        this.salary = salary;
    }


}

