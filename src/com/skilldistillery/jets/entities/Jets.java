package com.skilldistillery.jets.entities;

import java.util.Objects;

public abstract class Jets {
	private String model;
	private double speed;
	private int range;
	private long price;
	



	public Jets(String jetModel, double  jetSpeed, int jetRange, long jetPrice) {
		this.model = jetModel;
		this.speed = jetSpeed;
		this.range = jetRange;
		this.price = jetPrice;
	
	}
	
	public void fly() {
		System.out.println("Model: " + this.model + "\tMach speed: " + this.speed + "\tRange: " + this.range + "\tPrice: " + this.price );
	}
	
	public double getSpeedInMach() {
		double machSpeed = 0;
		
		return machSpeed;
		
		
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(model, price, range, speed);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jets other = (Jets) obj;
		return Objects.equals(model, other.model) && price == other.price && range == other.range
				&& Double.doubleToLongBits(speed) == Double.doubleToLongBits(other.speed);
	}
	
}