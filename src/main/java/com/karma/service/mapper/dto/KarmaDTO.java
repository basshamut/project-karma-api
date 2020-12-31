package com.karma.service.mapper.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "number", "missing", "situation", "improve"})
public class KarmaDTO {
    private int id;
    private int number;
    private String missing;
    private String situation;
    private String improve;

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
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
    
    
}
