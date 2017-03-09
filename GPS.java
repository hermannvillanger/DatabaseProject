package database;

public class GPS {

	public static String createGps(String Gps_Time, String Coordinates, Integer Pulse){
		return "INSERT INTO Gps (Gps_Time,Coordinates,Pulse)" +
				"VALUES (" + Gps_Time + "," + Coordinates + "," + Pulse + ")";
	}
	public static String createGps_Link(String Gps_Time, String Workout_Start){
		return "INSERT INTO Template (Gps_Time,Workout_Start)" +
				"VALUES (" + Gps_Time + "," + Workout_Start +")";
	}
	
}
