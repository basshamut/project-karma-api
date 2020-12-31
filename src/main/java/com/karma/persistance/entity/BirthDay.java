package com.karma.persistance.entity;

import javax.persistence.*;

@Entity
@Table(name = "birthday")
public class BirthDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String simbolType;
    private String sexInPast;
    private String location;
    private String day;
    private String currentSex;

    public BirthDay() {
    }

    public BirthDay(Integer id, String simbolType, String sexInPast, String location, String day, String currentSex) {
        this.id = id;
        this.simbolType = simbolType;
        this.sexInPast = sexInPast;
        this.location = location;
        this.day = day;
        this.currentSex = currentSex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSimbolType() {
        return simbolType;
    }

    public void setSimbolType(String simbolType) {
        this.simbolType = simbolType;
    }

    public String getSexInPast() {
        return sexInPast;
    }

    public void setSexInPast(String sexInPast) {
        this.sexInPast = sexInPast;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCurrentSex() {
        return currentSex;
    }

    public void setCurrentSex(String currentSex) {
        this.currentSex = currentSex;
    }
}
