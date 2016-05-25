package com.dmoffat.chapter4;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String color;
	
	@ManyToOne
	private Showroom showroom;
	
	public Showroom getShowroom() {
		return showroom;
	}

	public void setShowroom(Showroom showroom) {
		this.showroom = showroom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", color=");
		builder.append(color);
		builder.append("]");
		return builder.toString();
	}

}
