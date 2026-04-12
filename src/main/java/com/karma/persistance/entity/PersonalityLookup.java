package com.karma.persistance.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "personality_lookup")
public class PersonalityLookup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "simbol_code")
    private String simbolCode;

    @Column(name = "simbol_name")
    private String simbolName;

    @Column(name = "description_odd")
    private String descriptionOdd;

    @Column(name = "description_even")
    private String descriptionEven;

    public PersonalityLookup() {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getSimbolCode() { return simbolCode; }
    public void setSimbolCode(String simbolCode) { this.simbolCode = simbolCode; }

    public String getSimbolName() { return simbolName; }
    public void setSimbolName(String simbolName) { this.simbolName = simbolName; }

    public String getDescriptionOdd() { return descriptionOdd; }
    public void setDescriptionOdd(String descriptionOdd) { this.descriptionOdd = descriptionOdd; }

    public String getDescriptionEven() { return descriptionEven; }
    public void setDescriptionEven(String descriptionEven) { this.descriptionEven = descriptionEven; }
}
