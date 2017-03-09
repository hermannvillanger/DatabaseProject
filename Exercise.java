package database;

public class Exercise {

	public static String createExercise(String Exercise_Name,String Description,String Other ){
		return "INSERT INTO Exercise (Exercise_Name,Description,Climate,Other)" +
				"VALUES (" + Exercise_Name + "," + Description + "," + Other + ")";
	}	
	public static String createExerciseGroup(String Group_Name, String Description){
		return "INSERT INTO Workout_Contains (Group_Name,Description)" +
				"VALUES (" + Group_Name + "," + Description +")";
	}
	public static String createExerciseResult(String Strain, String Res_Date, Integer Execution_Nr, Integer Repetitions,
			String Exercise_Name, String Workout_Start){
		return "INSERT INTO Workout_Result (Strain,Res_Date,Execution_Nr,Repetitions,Exercise_Name,Workout_Start)" +
				"VALUES (" + Strain + "," + Res_Date + "," + Execution_Nr + "," + Repetitions + "," + 
				Exercise_Name + "," + Workout_Start +")";
	}
	public static String createGoal(String Goal, String Start_Date, String End_Date, Integer Goal_Id, String Exercise_Name){
		return "INSERT INTO Goal (Goal,Start_Date,End_Date,Goal_Id,Exercise_Name)" +
				"VALUES (" + Goal + "," + Start_Date + "," + End_Date + "," + Goal_Id + "," + 
				Exercise_Name + ")";
	}
	public static String createExercise_Group(String Group_Name, String Description){
		return "INSERT INTO Exercise_Group (Group_Name,Description)" +
				"VALUES (" + Group_Name + "," + Description + ")";
	}
	
}
