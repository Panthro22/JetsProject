package com.skilldistillery.jets.entities;

public class CargoPlane extends Jets implements CargoCarrier {

	public CargoPlane(String jetModel, double  jetSpeed, int jetRange, long jetPrice) {
		super(jetModel, jetSpeed, jetRange, jetPrice);
	}
	
	public void loadCargo(){
		System.out.println("Loading cargo on to " + this.getModel());
		System.out.println("Cargo loaded!");
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
}
