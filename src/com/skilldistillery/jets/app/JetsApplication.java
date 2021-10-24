package com.skilldistillery.jets.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.skilldistillery.jets.entities.*;

public class JetsApplication {
	
	private AirField airField = new AirField();
	private Scanner keyBoard = new Scanner(System.in);
	public static void main(String[] args) {
		JetsApplication jetA = new JetsApplication();
		jetA.luanchApp();
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void luanchApp() {
		bootUpPrompt();
		this.airField.setHangerSpace(readFromFile("jets.txt"));
		bootUpComplete();
		boolean runApp = true;
		while(runApp) {
			menuOptionsPrintout();
			runApp = userMenuChoice();
			
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void bootUpPrompt() {
		System.out.println("Welcome to Jet Control Tower Application.");
		System.out.println("Gathering Airfeild informaiton....");
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void bootUpComplete() {
		System.out.println("Informtion gather complete.");
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void menuOptionsPrintout() {
		System.out.println("1. List fleet:");
		System.out.println("2. Fly all jets:");
		System.out.println("3. Veiw fastest jet:");
		System.out.println("4. View jet with longest range:");
		System.out.println("5. Load all Cargo Jets:");
		System.out.println("6. Dogfight:");
		System.out.println("7. Add a jet to fleet:");
		System.out.println("8. Remove jet from fleet:");
		System.out.println("9. quit:");
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public boolean userMenuChoice() {
		int userChoice = 0;
		boolean runApp = true;
		System.out.print("Please enter a number for the option you would like to do: ");
		userChoice = keyBoard.nextInt();
		keyBoard.nextLine();
		switch(userChoice) {
		case 1:
			fleetList();
			break;
		case 2:
			flyFleet();
			break;
		case 3:
			getFastestJet();
			break;
		case 4:
			getLongestRangeJet();
			break;
		case 5:
			loadAllCargoPlanes();
			break;
		case 6:
			dogFight();
			break;
		case 7:
			addCustomPlane();
			break;
		case 8:
			removeJet();
			break;
		case 9:
			runApp = false;
			break;
		default:
			System.err.println("Invalid option put in... Try again");
			userMenuChoice();
			break;
		}
		return runApp;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void removeJet() {
		String userInputModel;
		System.out.println("Please enter the model of Jet to remove from the air field: ");
		userInputModel = keyBoard.nextLine();
		airField.remove(userInputModel);
	
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void getFastestJet() {
		Jets fastestJet = new FighterJet("Base", 0,0,0);
		for(Jets jet: this.airField.getHangerSpace()) {
			if(fastestJet.getSpeed() <= jet.getSpeed()) {
				fastestJet = jet;
			}
			
		}
		System.out.println("Fastest jet is " + fastestJet.getModel()+ " with top speed of: " +fastestJet.getSpeed());
 	}
	

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void getLongestRangeJet() {
		Jets longestRangeJet = new FighterJet("Base", 0,0,0);
		for(Jets jet: this.airField.getHangerSpace()) {
			if(longestRangeJet.getRange() <= jet.getRange()) {
				longestRangeJet = jet;
			}
			
		}
		System.out.println("Longest range jet is " + longestRangeJet.getModel()+ " with top range of: " +longestRangeJet.getRange());
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<Jets> readFromFile(String fn) {
		
	
		ArrayList<Jets> jetSepList = new ArrayList<>();

			ArrayList<String> jetSpecSep;
			
		try (BufferedReader bufIn = new BufferedReader(new FileReader(fn))) {
			String line;
			while ((line = bufIn.readLine()) != null) {
				String[] jetSpec = line.split(",");
				if(jetSpec[0].equals("Fighter")) {
					double jetSpeed = Double.valueOf(jetSpec[2]);
					int jetRange = Integer.valueOf(jetSpec[3]);
					long jetPrice = Long.valueOf(jetSpec[4]);
					FighterJet fJet = new FighterJet(jetSpec[1],jetSpeed,jetRange,jetPrice );
				
					jetSepList.add(fJet);
				}
				else if(jetSpec[0].equals("Cargo")) {
					double jetSpeed = Double.valueOf(jetSpec[2]);
					int jetRange = Integer.valueOf(jetSpec[3]);
					long jetPrice = Long.valueOf(jetSpec[4]);
					CargoPlane fJet = new CargoPlane(jetSpec[1],jetSpeed,jetRange,jetPrice );
				
					jetSepList.add(fJet);
				}
				else {
					System.err.println("Unable to identify Plane");
				}
			}
		} catch (IOException e) {
			System.err.println(e);
			}
		return jetSepList;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void fleetList() {
		for(Jets jet: this.airField.getHangerSpace()) {
			jet.fly();
		}
		
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void flyFleet() {
		for(Jets jet: this.airField.getHangerSpace()) {
			jet.fly();
			System.out.printf("Flight time in hours: %.2f\n",getFlightTime(jet));
		}
		
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public double getFlightTime(Jets jet) {
		double hours = 0;
		
		double speed = (jet.getSpeed() * 768);//768 is about average speed conversion from Mach.
		int range = (jet.getRange());
		
		hours = range/speed;
		
		return hours;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void loadAllCargoPlanes() {
		CargoPlane cargobase = new CargoPlane("Cargoplane", 0, 0, 0);
		for(Jets jet: this.airField.getHangerSpace()) {
			if((boolean)(jet).getClass().equals(cargobase.getClass())) {
				((CargoPlane) jet).loadCargo();
			}
			else {
				//continue searching for other cargo planes to be loaded
			}
		}
		
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void dogFight() {
		FighterJet fighterPlanes = new FighterJet("FighterPlane", 0, 0, 0);
		for(Jets jet: this.airField.getHangerSpace()) {
			if((boolean)(jet).getClass().equals(fighterPlanes.getClass())) {
				((FighterJet) jet).fight();
			}
			else {
				//continue searching for other cargo planes to be loaded
			}
		}
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void addCustomPlane() {
		int userChoice;

		System.out.println("Welcome to the custom plane builder.");
		System.out.println("Your able to build an additional Fighter, CargoPlane, or Passenger Plane to the fleet");
		System.out.println("Please enter the number for which jet to make: \n1.Fighter Jet \n2.Cargo Jet\n3.Passenger Jet");
		userChoice = keyBoard.nextInt();
		keyBoard.nextLine();
		switch(userChoice){
			case 1:
				buildPlane("Fighter");
				break;
			case 2:
				buildPlane("CargoPlane");
				break;
			case 3:
				buildPlane("JetPassenger");
				break;
			default:
				System.out.println("not sure we both agree on the \"plane\" ....");
				break;
		}
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void buildPlane(String jetType) {
		String planeModel;
		double planeMachSpeed;
		int planeRange;
		long planePrice;
		
		System.out.println("Please enter the model of Jet: ");
		planeModel = keyBoard.nextLine();
		System.out.println("Please enter the Mach Speed of jet: ");
		planeMachSpeed = keyBoard.nextDouble();
		keyBoard.nextLine();
		System.out.println("Please enter the Jets range of flight in  whole miles: ");
		planeRange = keyBoard.nextInt();
		keyBoard.nextLine();
		System.out.println("Please enter the price of the Jet: ");
		planePrice = keyBoard.nextLong();
		keyBoard.nextLine();
		if(jetType.equals("Fighter")) {
			Jets fJet = new FighterJet(planeModel,planeMachSpeed,planeRange,planePrice );
			airField.add(fJet);
		}
		else if ( jetType.equals("CargoPlane")) {
			CargoPlane fJet = new CargoPlane(planeModel,planeMachSpeed,planeRange,planePrice );
			airField.add(fJet);
		}
		else {
			CargoPlane fJet = new CargoPlane(planeModel,planeMachSpeed,planeRange,planePrice );
			airField.add(fJet);
		}
		System.out.println(planeModel + " was added to the airfield.");
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
