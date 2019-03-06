package com.microland.iiot.nb.services.dto;

public class DBEntity{
	private int cost;
	private String name;
	
	public DBEntity() {}
	
	public DBEntity(int cost, String name) {
		super();
		this.cost = cost;
		this.name = name;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

