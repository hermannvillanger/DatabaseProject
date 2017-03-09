package database;

import java.sql.*;

public class Driver {

	public static void main(String[] args) {

		try{
			// 1. Get a connection to database
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdatabase?autoReconnect=true&useSSL=false","project","project");
			boolean makeChanges = true;
			while(makeChanges){
				// 2. Create a statement
				Statement myStmt = myConn.createStatement();
				// 3. Execute SQL query
				
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
