package database;

import java.util.Scanner;

public class GPS {
	static Scanner scanner;
	
	public static String createGps(){
		System.out.println("Create Gps result");
		System.out.println("Gps Time:");
		System.out.println(">");
		String Gps_Time = scanner.nextLine();
		System.out.println("Coordinates:");
		System.out.println(">");
		String Coordinates = scanner.nextLine();
		System.out.println("Pulse:");
		System.out.println(">");
		String Pulse = scanner.nextLine();
		
		return "INSERT INTO Gps (Gps_Time,Coordinates,Pulse)" +
				"VALUES (" + Gps_Time + "," + Coordinates + "," + Pulse + ")";
	}
	public static String createGps_Link(){
		System.out.println("Add To Gps");
		System.out.println("Gps Time:");
		System.out.println(">");
		String Gps_Time = scanner.nextLine();
		System.out.println("Workout Start:");
		System.out.println(">");
		String Workout_Start = scanner.nextLine();
		return "INSERT INTO Template (Gps_Time,Workout_Start)" +
				"VALUES (" + Gps_Time + "," + Workout_Start +")";
	}
	
}
