package database;

import java.sql.*;
import java.util.Scanner;

public class Driver {
	Scanner scanner;
	Connection myConn; 
	Statement myStmt;
		
		public static void main(String[] args) {
			Driver driver = new Driver();
			driver.init();
			driver.run();
		}
		void init(){
			scanner = new Scanner(System.in);
			
		}
		void run(){
			try{
				// 1. Get a connection to database
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdatabase?autoReconnect=true&useSSL=false","project","project");
				
				boolean makeChanges = true;
				while(makeChanges){
					// 2. Create a statement
					myStmt = myConn.createStatement();
					// 3. Execute SQL query
					
					//Still spørsmål
					
					ResultSet myRs = myStmt.executeQuery("select * from exercise");
					// 4. Process the result set
					while(myRs.next()){
						System.out.println(myRs.getString("exercise_name") + "," + myRs.getString("description"));
					}	
					makeChanges = false;
				}
			}
			catch(Exception exc){
				exc.printStackTrace();
			}
		}
		
		public Integer getSize(String table) throws SQLException{
			ResultSet myRs = myStmt.executeQuery("select count(*) from " + table);
			return Integer.parseInt(myRs.getString(0)) + 1;
		}
		
	//EXERCISE
		public String createExercise(){
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
		public String createExerciseGroup(){
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
		public String createExerciseResult(){
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
		public String createGoal() throws SQLException{
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
			
			Integer Goal_Id = getSize("Goal");

			System.out.println("Exercise Name:");
			System.out.println(">");
			String Exercise_Name = scanner.nextLine();
			
			return "INSERT INTO Goal (Goal,Start_Date,End_Date,Goal_Id,Exercise_Name)" +
					"VALUES (" + Goal + "," + Start_Date + "," + End_Date + "," + Goal_Id + "," + 
					Exercise_Name + ")";
		}
		public String insertExerciseInGroup(){
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
	//WORKOUT
		public String createNote() throws SQLException{
			System.out.println("Create Note");
			
			Integer Note_Id = getSize("Note");

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
		public String createWorkout(){
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
		public String createWorkoutContains(){
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
	//TEMPLATE
		public String createTemplate() throws SQLException{
			System.out.println("Create Template");
			System.out.println("Template Name:");
			System.out.println(">");
			String Template_Name = scanner.nextLine();
			
			Integer Template_Id = getSize("Template");
			
			return "INSERT INTO Template (Template_Name,Template_Id)" +
					"VALUES (" + Template_Name + "," + Template_Id +")";
		}
		public String addToTemplate() throws SQLException{
			System.out.println("Add To Template");
			System.out.println("Exercise Name:");
			System.out.println(">");
			String Exercise_Name = scanner.nextLine();
			Integer Template_Id = getSize("Template");

			return "INSERT INTO Template_Contains (Exercise_Name,Template_Id)" +
					"VALUES (" + Exercise_Name + "," + Template_Id + ")";
		}
	//GPS
		public String createGps(){
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
		public String createGps_Link(){
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
//DELETE
	
	private String DeleteExercise(String exercise) throws SQLException{
		return "delete * from exercise where Exercise_Name="+exercise;
	}
	private String DeleteWorkout(String workout_start) throws SQLException{
		return "delete * from Workout where Workout_Start=" + workout_start;
	}
	private String DeleteTemplate(String template_id) throws SQLException{
		return "delete * from Template where Template_Id=" + template_id;
	}
	private String DeleteNote(String note)throws SQLException{
		return "delete * from Note where Note_Id="+note;
	}
	private String DeleteGoal(String goal) throws SQLException{
		return "delete * from Goal where Goal_Id=" + goal;
	}
	private String DeleteGPS(String Gps){
		return "delete * from GPS where GPS_Time="+Gps;
	}
	
}