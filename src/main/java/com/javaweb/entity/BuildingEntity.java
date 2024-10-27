package com.javaweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "street")
    private String street;
    @Column(name = "ward")
    private String ward;
    @Column(name = "numberofbasement")
    private Integer numberOfBasement;
    @Column(name = "managername")
    private String managername;
    @Column(name = "managerphone")
    private String managerphone;
    @Column(name = "floorarea")
    private Integer floorarea;
    @Column(name = "rentprice")
    private Integer rentprice;
    @Column(name = "servicefee")
    private String servicefee;
    @Column(name = "brokeragefee")
    private Double brokeragefee;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentbuilding",
               joinColumns = @JoinColumn(name = "buildingid", nullable = false),
                inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> userEntities = new ArrayList<>();

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }
    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }
    public String getWard() {
        return ward;
    }
    public void setWard(String ward) {
        this.ward = ward;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getManagername() {
        return managername;
    }
    public void setManagername(String managername) {
        this.managername = managername;
    }
    public String getManagerphonenumber() {
        return managerphone;
    }
    public void setManagerphonenumber(String managerphonenumber) {
        this.managerphone = managerphonenumber;
    }
    public Integer getFloorarea() {
        return floorarea;
    }
    public void setFloorarea(Integer floorarea) {
        this.floorarea = floorarea;
    }
    public Integer getRentprice() {
        return rentprice;
    }
    public void setRentprice(Integer rentprice) {
        this.rentprice = rentprice;
    }
    public String getServicefee() {
        return servicefee;
    }
    public void setServicefee(String servicefee) {
        this.servicefee = servicefee;
    }
    public Double getBrokeragefee() {
        return brokeragefee;
    }
    public void setBrokeragefee(Double brokeragefee) {
        this.brokeragefee = brokeragefee;
    }
}