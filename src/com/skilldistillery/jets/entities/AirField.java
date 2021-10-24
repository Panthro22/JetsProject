package com.skilldistillery.jets.entities;

import java.util.ArrayList;
import java.util.List;

public class AirField {
	
	private ArrayList<Jets> hangerSpace;
	
	public void Airfeild() {		
		this.hangerSpace = new ArrayList<Jets>();
	}

//	public void add(ArrayList<Jets> readFromFile) {
//		
//		for(Jets jet:readFromFile) {
//			this.hangerSpace.add(jet);
//		}
//	}
	
	public void add(Jets jet) {
		hangerSpace.add(jet);
		
	}
	public void remove(String model) {
		
		for(Jets jets:hangerSpace) {
			
			if(jets.getModel().equals(model)){
				hangerSpace.remove(jets);
				System.out.println(model + " has been removed from the airfield.");
				return;
			}
			else {
				//search continues
			
			}
		}
		System.out.println(model + " was not found on the airfield, Please check spelling of name.");
	}

	public ArrayList<Jets> getHangerSpace() {
		return this.hangerSpace;
	}

	public void setHangerSpace(ArrayList<Jets> hangerSpace) {
		this.hangerSpace = hangerSpace;
	}
	
}
