package database;

public class Workout {
	
	public static String createNote(Integer Note_Id, String Workout_Start, String Purpose, String Tips){
		return "INSERT INTO Note (Note_Id,Purpose,Tips)" +
				"VALUES (" + Note_Id + "," + Purpose + "," + Tips +")";
	}
	public static String createWorkout(String Workout_Start, String Workout_End, Integer Shape, Integer Performance,
			Integer Template_Id){
		return "INSERT INTO Workout (Workout_Start,Workout_End,Shape,Performance,Note_Id)" +
				"VALUES (" + Workout_Start + "," + Workout_End + "," + Shape+ "," + Performance+ ")";
	}
	public static String createWorkoutContains(String Exercise_Name, String Workout_Start){
		return "INSERT INTO Workout_Contains (Exercise_Name,Workout_Start)" +
				"VALUES (" + Exercise_Name + "," + Workout_Start +")";
	}
	
	
	
	
	
}
