package database;

import java.util.Scanner;

public class Workout {
	static Scanner scanner;
	
	public static String createNote(){
		System.out.println("Create Note");
System.out.println("Note Id:");
System.out.println(">");
String Note_Id = scanner.nextLine();
		System.out.println("Workout Start:");
		System.out.println(">");
		String Workout_Start = scanner.nextLine();
		System.out.println("Purpose:");
		System.out.println(">");
		String Purpose = scanner.nextLine();
		System.out.println("Tips:");
		System.out.println(">");
		String Tips = scanner.nextLine();
		
		return "INSERT INTO Note (Note_Id,Workout_Start,Purpose,Tips)" +
				"VALUES (" + Note_Id + "," + Workout_Start + "," + Purpose + "," + Tips +")";
	}
	public static String createWorkout(){
		System.out.println("Create Workout");
		System.out.println("Workout Start:");
		System.out.println(">");
		String Workout_Start = scanner.nextLine();
		System.out.println("Workout End:");
		System.out.println(">");
		String Workout_End = scanner.nextLine();
		
		System.out.println("Shape:");
		System.out.println(">");
		String Shape = scanner.nextLine();
		System.out.println("Performance:");
		System.out.println(">");
		String Performance = scanner.nextLine();
		
		System.out.println("Template Id:");
		System.out.println(">");
		String Template_Id = scanner.nextLine();
		System.out.println("Climate:");
		System.out.println(">");
		String Climate = scanner.nextLine();
		
		return "INSERT INTO Workout (Workout_Start,Workout_End,Shape,Performance,Template_Id,Climate)" +
				"VALUES (" + Workout_Start + "," + Workout_End + "," + Shape+ "," + Performance +
				Template_Id + "," + Climate + ")";
	}
	public static String createWorkoutContains(){
		System.out.println("Insert Exercise into Workout");
		System.out.println("Exercise name:");
		System.out.println(">");
		String Exercise_Name = scanner.nextLine();
		System.out.println("Workout start:");
		System.out.println(">");
		String Workout_Start = scanner.nextLine();
		
		return "INSERT INTO Workout_Contains (Exercise_Name,Workout_Start)" +
				"VALUES (" + Exercise_Name + "," + Workout_Start +")";
	}
	
}
