package database;

import java.sql.*;
import java.util.Scanner;

public class Driver {
	static Scanner scanner;
	static Connection myConn; 
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
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		
		try{
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdatabase?autoReconnect=true&useSSL=false","project","project");
			
			boolean makeChanges = true;
			while(makeChanges){
				// 2. Create a statement
				Statement myStmt = myConn.createStatement();
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
}