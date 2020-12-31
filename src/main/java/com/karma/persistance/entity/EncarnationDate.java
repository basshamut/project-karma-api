package com.karma.persistance.entity;

import javax.persistence.*;

@Entity
@Table(name="encarnationdate")
public class EncarnationDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String orientationType;
    private String letterCode;
    private String yearEncarnation;

    public EncarnationDate() {
    }

    public EncarnationDate(Integer id, String orientationType, String letterCode, String yearEncarnation) {
        this.id = id;
        this.orientationType = orientationType;
        this.letterCode = letterCode;
        this.yearEncarnation = yearEncarnation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getYearEncarnation() {
        return yearEncarnation;
    }

    public void setYearEncarnation(String yearEncarnation) {
        this.yearEncarnation = yearEncarnation;
    }
}
