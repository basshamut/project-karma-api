package com.karma.service.mapper.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"sexInPastLife", "country", "yearApprox", "profession", "personalitySymbol", "personality"})
public class PastLifeDTO {

    private String sexInPastLife;
    private String country;
    private String yearApprox;
    private String profession;
    private String personalitySymbol;
    private String personality;

    public String getSexInPastLife() { return sexInPastLife; }
    public void setSexInPastLife(String sexInPastLife) { this.sexInPastLife = sexInPastLife; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getYearApprox() { return yearApprox; }
    public void setYearApprox(String yearApprox) { this.yearApprox = yearApprox; }

    public String getProfession() { return profession; }
    public void setProfession(String profession) { this.profession = profession; }

    public String getPersonalitySymbol() { return personalitySymbol; }
    public void setPersonalitySymbol(String personalitySymbol) { this.personalitySymbol = personalitySymbol; }

    public String getPersonality() { return personality; }
    public void setPersonality(String personality) { this.personality = personality; }
}
