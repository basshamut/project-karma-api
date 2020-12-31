package com.karma.persistance.entity;

import javax.persistence.*;

@Entity
@Table(name="karma")
public class Karma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "number")
    private Integer number;
    @Column(name = "missing")
    private String missing;
    @Column(name = "situation")
    private String situation;
    @Column(name = "improve")
    private String improve;

    public Karma() {
    }

    public Karma(Integer id, Integer number, String missing, String situation, String improve) {
        this.id = id;
        this.number = number;
        this.missing = missing;
        this.situation = situation;
        this.improve = improve;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public String getMissing() {
        return missing;
    }
    public void setMissing(String missing) {
        this.missing = missing;
    }
    public String getSituation() {
        return situation;
    }
    public void setSituation(String situation) {
        this.situation = situation;
    }
    public String getImprove() {
        return improve;
    }
    public void setImprove(String improve) {
        this.improve = improve;
    }

    @Override
    public String toString() {
        return "Karma [id=" + id + ", number=" + number + ", missing=" + missing + ", situation=" + situation
                + ", improve=" + improve + "]";
    }
}
