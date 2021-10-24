package com.skilldistillery.jets.entities;

public class FighterJet extends Jets implements CombatReady{
	
	
	public FighterJet(String jetModel, double  jetSpeed, int jetRange, long jetPrice) {
		super(jetModel, jetSpeed, jetRange, jetPrice);
	}
	
	public void fight() {
		System.out.println(this.getModel() + " Firing FoxTrot One!");
		System.out.println("Incoming Missle Detected");
		System.out.println("Deploying Flares!");
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
