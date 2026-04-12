package com.karma.persistance.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "profession_lookup")
public class ProfessionLookup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "description_male")
    private String descriptionMale;

    @Column(name = "description_female")
    private String descriptionFemale;

    public ProfessionLookup() {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescriptionMale() { return descriptionMale; }
    public void setDescriptionMale(String descriptionMale) { this.descriptionMale = descriptionMale; }

    public String getDescriptionFemale() { return descriptionFemale; }
    public void setDescriptionFemale(String descriptionFemale) { this.descriptionFemale = descriptionFemale; }
}
