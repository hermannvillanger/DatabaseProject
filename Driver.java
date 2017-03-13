package database;

import java.sql.*;
import java.util.Scanner;

public class Driver {
	static Scanner scanner;
	static Connection myConn; 
	
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
