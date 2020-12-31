package com.karma.persistance.entity;

import javax.persistence.*;

@Entity
@Table(name="birthmounth")
public class BirthMounth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String mounth;
    private String profesion;
    private String simbolType;
    private String orientationType;
    private String letterCode;
    private String sexInPast;
    private String currentSex;

    public BirthMounth() {
    }

    public BirthMounth(Integer id, String mounth, String profesion, String simbolType, String orientationType, String letterCode, String sexInPast, String currentSex) {
        this.id = id;
        this.mounth = mounth;
        this.profesion = profesion;
        this.simbolType = simbolType;
        this.orientationType = orientationType;
        this.letterCode = letterCode;
        this.sexInPast = sexInPast;
        this.currentSex = currentSex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMounth() {
        return mounth;
    }

    public void setMounth(String mounth) {
        this.mounth = mounth;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getSimbolType() {
        return simbolType;
    }

    public void setSimbolType(String simbolType) {
        this.simbolType = simbolType;
    }

    public String getOrientationType() {
        return orientationType;
    }

    public void setOrientationType(String orientationType) {
        this.orientationType = orientationType;
    }

    public String getLetterCode() {
        return letterCode;
    }

    public void setLetterCode(String letterCode) {
        this.letterCode = letterCode;
    }

    public String getSexInPast() {
        return sexInPast;
    }

    public void setSexInPast(String sexInPast) {
        this.sexInPast = sexInPast;
    }

    public String getCurrentSex() {
        return currentSex;
    }

    public void setCurrentSex(String currentSex) {
        this.currentSex = currentSex;
    }
}
