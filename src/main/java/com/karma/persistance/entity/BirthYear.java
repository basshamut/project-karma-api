package com.karma.persistance.entity;

import javax.persistence.*;

@Entity
@Table(name="birthyear")
public class BirthYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String yearPreffix;
    private String yearSuffix;
    private String letterCode;
    private String currentSex;

    public BirthYear() {
    }

    public BirthYear(Integer id, String yearPreffix, String yearSuffix, String letterCode, String currentSex) {
        this.id = id;
        this.yearPreffix = yearPreffix;
        this.yearSuffix = yearSuffix;
        this.letterCode = letterCode;
        this.currentSex = currentSex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYearPreffix() {
        return yearPreffix;
    }

    public void setYearPreffix(String yearPreffix) {
        this.yearPreffix = yearPreffix;
    }

    public String getYearSuffix() {
        return yearSuffix;
    }

    public void setYearSuffix(String yearSuffix) {
        this.yearSuffix = yearSuffix;
    }

    public String getLetterCode() {
        return letterCode;
    }

    public void setLetterCode(String letterCode) {
        this.letterCode = letterCode;
    }

    public String getCurrentSex() {
        return currentSex;
    }

    public void setCurrentSex(String currentSex) {
        this.currentSex = currentSex;
    }
}
