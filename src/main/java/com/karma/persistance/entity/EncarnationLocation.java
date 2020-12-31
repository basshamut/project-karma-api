package com.karma.persistance.entity;

import javax.persistence.*;

@Entity
@Table(name="encarnationlocation")
public class EncarnationLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String location;
    private String locationName;

    public EncarnationLocation() {
    }

    public EncarnationLocation(Integer id, String location, String locationName) {
        this.id = id;
        this.location = location;
        this.locationName = locationName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
