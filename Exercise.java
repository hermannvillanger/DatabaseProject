package database;

import java.util.Scanner;

public class Exercise {
	
	static Scanner scanner;

	public static String createExercise(){
		System.out.println("Create Exercise");
		System.out.println("Exercise Name:");
		System.out.println(">");
		String Exercise_Name = scanner.nextLine();
		System.out.println("Description:");
		System.out.println(">");
		String Description = scanner.nextLine();
		System.out.println("Other:");
		System.out.println(">");
		String Other = scanner.nextLine();
		
		return "INSERT INTO Exercise (Exercise_Name,Description,Climate,Other)" +
				"VALUES (" + Exercise_Name + "," + Description + "," + Other + ")";
	}	
	public static String createExerciseGroup(){
		System.out.println("Create Exercise Group");
		System.out.println("Group Name:");
		System.out.println(">");
		String Group_Name = scanner.nextLine();
		System.out.println("Description:");
		System.out.println(">");
		String Description = scanner.nextLine();
		
		return "INSERT INTO Workout_Contains (Group_Name,Description)" +
				"VALUES (" + Group_Name + "," + Description +")";
	}
	public static String createExerciseResult(){
		System.out.println("Exercise Result");
		System.out.println("Strain:");
		System.out.println(">");
		String Strain = scanner.nextLine();
		System.out.println("Res Date:");
		System.out.println(">");
		String Res_Date = scanner.nextLine();
		System.out.println("Execution Nr");
		System.out.println(">");
		String Execution_Nr = scanner.nextLine();
		System.out.println("Repetitions:");
		System.out.println(">");
		String Repetitions = scanner.nextLine();
		System.out.println("Exercise Name:");
		System.out.println(">");
		String Exercise_Name = scanner.nextLine();
		System.out.println("Workout Start:");
		System.out.println(">");
		String Workout_Start = scanner.nextLine();
		
		return "INSERT INTO Workout_Result (Strain,Res_Date,Execution_Nr,Repetitions,Exercise_Name,Workout_Start)" +
				"VALUES (" + Strain + "," + Res_Date + "," + Execution_Nr + "," + Repetitions + "," + 
				Exercise_Name + "," + Workout_Start +")";
	}
	public static String createGoal(){
		System.out.println("Create Goal");
		System.out.println("Goal:");
		System.out.println(">");
		String Goal = scanner.nextLine();
		System.out.println("Start Date:");
		System.out.println(">");
		String Start_Date = scanner.nextLine();
		System.out.println("End Date");
		System.out.println(">");
		String End_Date = scanner.nextLine();
System.out.println("Goal Id:");
System.out.println(">");
String Goal_Id = scanner.nextLine();
		System.out.println("Exercise Name:");
		System.out.println(">");
		String Exercise_Name = scanner.nextLine();
		
		return "INSERT INTO Goal (Goal,Start_Date,End_Date,Goal_Id,Exercise_Name)" +
				"VALUES (" + Goal + "," + Start_Date + "," + End_Date + "," + Goal_Id + "," + 
				Exercise_Name + ")";
	}
	public static String insertExerciseInGroup(){
		System.out.println("Insert Exercise into Group");
		System.out.println("Exercise Name:");
		System.out.println(">");
		String Exercise_Name = scanner.nextLine();
		System.out.println("Group Name:");
		System.out.println(">");
		String Group_Name = scanner.nextLine();
		
		return "INSERT INTO Exercise_Group (Exercise_Name,Group_Name)" +
				"VALUES (" + Exercise_Name + "," + Group_Name + ")";
	}
	
}


