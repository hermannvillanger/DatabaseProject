package database;

import java.sql.*;
import java.util.ArrayList;
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
 				System.out.println("What do you want to do?");
 				System.out.println("1: Create Template");
 				System.out.println("2: Create Exercise");
 				System.out.println("3: Create Workout");
 				System.out.println("10: Exit");
 				Integer num=scanner.nextInt();
 				switch (num){
 				case 1: template_creation();
				break;
// 				case 2: exercise_creation();
// 				break;
 				case 3: workoutCreation();
 				break;
 				case 10: makeChanges = false;
 				break;
 				}
 			}
 		}
 		catch(Exception exc){
 			exc.printStackTrace();
 		}
 	}
//TODO CREATE TABLE
	public String createExercise(){
		System.out.println("Create Exercise");
		String Exercise_Name = getExerciseName();
		String Description = getDescription();
		
		return "INSERT INTO Exercise (Exercise_Name,Description) " +
		"\nVALUES (" + Exercise_Name + "," + Description + ")";
	}
	public String createExerciseGroup(){
		System.out.println("Create Exercise Group");
		String Group_Name = getGroupName();
		String Description = getDescription();
		
		return "INSERT INTO Workout_Contains (Group_Name,Description) " +
		"\nVALUES (" + Group_Name + "," + Description +")";
	}
	public String createExerciseResult(){
		System.out.println("Exercise Result");
		String Strain = getStrain();
		String Res_Date = getSpecificDate("Res");
		String Execution_Nr = getExecutionNr();
		String Repetitions = getRepetitions();
		String Exercise_Name = getExerciseName();
		String Workout_Start = getWorkoutStart();
		
		return "INSERT INTO Workout_Result (Strain,Res_Date,Execution_Nr,Repetitions,Exercise_Name,Workout_Start) " +
		"\nVALUES (" + Strain + "," + Res_Date + "," + Execution_Nr + "," + Repetitions + "," + 
		Exercise_Name + "," + Workout_Start +")";
	}
	public String createGoal(){
		System.out.println("Create Goal");
		String Goal = getGoal();
		String Start_Date = getSpecificDate("Start");
		String End_Date = getSpecificDate("End");
		String Exercise_Name = getExerciseName();
		
		return "INSERT INTO Goal (Goal,Start_Date,End_Date,Exercise_Name) " +
		"VALUES (" + Goal + "," + Start_Date + "," + End_Date + "," + Exercise_Name + ")";
	}
	public String insertExerciseInGroup(){
		System.out.println("Insert Exercise into Group");
		String Exercise_Name = getExerciseName();
		String Group_Name = getGroupName();
		
		return "INSERT INTO Exercise_Group (Exercise_Name,Group_Name) " +
		"\nVALUES (" + Exercise_Name + "," + Group_Name + ")";
	}
	public String createNote(){
		System.out.println("Create Note");
		String Workout_Start = getWorkoutStart();
		String Purpose = getPurpose();
		String Tips = getTips();
		
		return "INSERT INTO Note (Workout_Start,Purpose,Tips) " +
		"\nVALUES (" + Workout_Start + "," + Purpose + "," + Tips +")";
	}
	public String createWorkout(){
		System.out.println("Create Workout");
		String Workout_Start = getWorkoutStart();
		String Workout_End = getWorkoutEnd();
		String Shape = getShape();
		String Performance = getPerformance();
		String Template_Id = printTemplatesId();
		String Climate = getClimate();
		
		return "INSERT INTO Workout (Workout_Start,Workout_End,Shape,Performance,Template_Id,Climate) " +
		"\nVALUES (" + Workout_Start + "," + Workout_End + "," + Shape + "," + Performance + "," +
		Template_Id + "," + Climate + ")";
	}
	public String createWorkoutContains(String Workout_Start,String Exercise_Name){		
		return "INSERT INTO Workout_Contains (Exercise_Name,Workout_Start) " +
		"\nVALUES (" + Exercise_Name + "," + Workout_Start +")";
	}
	public String createTemplate(String Template_Name){
		return "INSERT INTO Template (Template_Name) " +
		"\nVALUES (" + Template_Name + ")";
	}
	public String addToTemplate(int id,String Template_Id){
		return "INSERT INTO Template_Contains (Exercise_Name,Template_Id) " +
		"\nVALUES (" + id + "," + Template_Id + ")";
	}
	public String createGps(){
		System.out.println("Create Gps result");
		String Gps_Time = getGpsTime();
		String Coordinates = getCoordinates();
		String Pulse = getPulse();
		
		return "INSERT INTO Gps (Gps_Time,Coordinates,Pulse) " +
		"\nVALUES (" + Gps_Time + "," + Coordinates + "," + Pulse + ")";
	}
	public String createGps_Link(){
		System.out.println("Add To Gps");
		String Gps_Time = getGpsTime();
		String Workout_Start = getWorkoutStart();
		return "INSERT INTO Template (Gps_Time,Workout_Start) " +
		"\nVALUES (" + Gps_Time + "," + Workout_Start +")";
	}
//TODO END CREATE TABLE
//TODO GET FROM USER
	private String getDescription() {
		System.out.println("Description:");
		System.out.print(">");
		String Description = "'" + scanner.nextLine() + "'";
		return Description;
	}
	private String getExerciseName() {
		System.out.println("Exercise Name:");
		System.out.print(">");
		String Exercise_Name = "'" + scanner.nextLine() + "'";
		return Exercise_Name;
	}
	private String getGroupName() {
		System.out.println("Group Name:");
		System.out.print(">");
		String Group_Name = "'" + scanner.nextLine() + "'";
		return Group_Name;
	}
	private String getWorkoutStart() {
		System.out.println("Workout Start, Date: 'YYYY-MM-DD' :");
		System.out.print(">");
		String Date = scanner.nextLine();
		System.out.println("Workout Start, Time: 'HH:MM:SS' :");
		System.out.print(">");
		String Time = scanner.nextLine();
		String Workout_Start = "'" + Date + " " + Time + "'";
		return Workout_Start;
	}
	private String getWorkoutEnd() {
		System.out.println("Workout End, Date: 'YYYY-MM-DD' :");
		System.out.print(">");
		String Date = scanner.nextLine();
		System.out.println("Workout End, Time: 'HH:MM:SS' :");
		System.out.print(">");
		String Time = scanner.nextLine();
		String Workout_End = "'" + Date + " " + Time + "'";
		return Workout_End;
	}
	private String getRepetitions() {
		System.out.println("Repetitions:");
		System.out.print(">");
		String Repetitions = scanner.nextLine();
		return Repetitions;
	}
	private String getExecutionNr() {
		System.out.println("Execution Nr");
		System.out.print(">");
		String Execution_Nr = scanner.nextLine();
		return Execution_Nr;
	}
	private String getSpecificDate(String date) {
		System.out.println(date + " Date:");
		System.out.print(">");
		String Date = "'" + scanner.nextLine() + "'";
		return Date;
	}
	private String getStrain() {
		System.out.println("Strain:");
		System.out.print(">");
		String Strain = scanner.nextLine();
		return Strain;
	}
	private String getGoal() {
		System.out.println("Goal:");
		System.out.print(">");
		String Goal = scanner.nextLine();
		return Goal;
	}
	private String getTips() {
		System.out.println("Tips:");
		System.out.print(">");
		String Tips = "'" + scanner.nextLine() + "'";
		return Tips;
	}
	private String getPurpose() {
		System.out.println("Purpose:");
		System.out.print(">");
		String Purpose = "'" + scanner.nextLine() + "'";
		return Purpose;
	}
	private String getClimate() {
		System.out.println("Climate:");
		System.out.print(">");
		String Climate = "'" + scanner.nextLine() + "'";
		return Climate;
	}
	private String printTemplatesId() {
		System.out.println("Template Id:");
		System.out.print(">");
		String Template_Id = scanner.nextLine();
		return Template_Id;
	}
	private String getPerformance() {
		System.out.println("Performance:");
		System.out.print(">");
		String Performance = scanner.nextLine();
		return Performance;
	}
	private String getShape() {
		System.out.println("Shape:");
		System.out.print(">");
		String Shape = scanner.nextLine();
		return Shape;
	}
	private String printTemplatesName() {
		System.out.println("Template Name:");
		System.out.print("> ");
		String Template_Name = "'" + scanner.nextLine() + "'";
		return Template_Name;
	}
	private String getPulse() {
		System.out.println("Pulse:");
		System.out.print(">");
		String Pulse = scanner.nextLine();
		return Pulse;
	}
	private String getCoordinates() {
		System.out.println("Coordinates:");
		System.out.print(">");
		String Coordinates = "'" + scanner.nextLine() + "'";
		return Coordinates;
	}
	private String getGpsTime() {
		System.out.println("Gps Time:");
		System.out.print(">");
		String Gps_Time = "'" + scanner.nextLine() + "'";
		return Gps_Time;
	}
//TODO TIL MULIG FORENKLING AV KODEN
//	private String getInteger(String What) {
//		System.out.println(What + ":(Integer)");
//		System.out.print(">");
//		What = scanner.nextLine();
//		return What;
//	}
//	private String getString(String What) {
//	System.out.println(What + ": (String)");
//	System.out.print(">");
//	What = "'"  + scanner.nextLine() + "'" ;
//	return What;
//}
	
//TODO END GET FROM USER
//TODO PRINT
	public void printExercises() throws SQLException{
		myStmt = myConn.createStatement();
		
		ResultSet myRs = myStmt.executeQuery("select * from Exercise");
		System.out.println("Exercises:");
		while(myRs.next()){
			System.out.println(myRs.getString("Exercise_Name") + ": " + myRs.getString("Description"));
		}
	}	
	public void printWorkout(String Workout_Start) throws SQLException{
		myStmt = myConn.createStatement();
		
		ResultSet myRs = myStmt.executeQuery("select * from Workout where Workout.Workout_Start = " +  Workout_Start);
		System.out.println("Workout_Start, Workout_End, Shape, Performance, Template_Id, Climate");
		myRs.next();
		System.out.println( myRs.getString("Workout_Start") + "," + myRs.getString("Workout_End") + 
		"," + myRs.getString("Shape") + "," + myRs.getString("Performance") + 
		"," + myRs.getString("Template_Id") + "," + myRs.getString("Climate") );
		String query = "select exercise.Exercise_Name,Description "
				+ "from Workout Join Workout_Contains on Workout.Workout_Start = Workout_Contains.Workout_Start "
				+ " Join Exercise on Exercise.Exercise_Name = Workout_Contains.Exercise_Name "+
				"where Workout.Workout_Start =" +  Workout_Start;
		myRs = myStmt.executeQuery(query);
		System.out.println("Exercises:");
		while(myRs.next()){
			System.out.println( myRs.getString("Exercise_Name") + ": " + myRs.getString("Description") );
		}
		
	}
//TODO END PRINT
//TODO GET RESULT/GOAL INFORMATION
	public void getResults(String Exercise_Name) throws SQLException{
		ResultSet results = myStmt.executeQuery("SELECT * FROM Workout_Result WHERE Exercise_Name = " + Exercise_Name);
		System.out.println("Results for " + Exercise_Name);
		while(results.next()){
			System.out.println(results.getString("Res_Date") + "," +results.getString("Strain") + "," + results.getString("Unit") + "," + results.getString("Repetitions"));
		}	
	}
	
	public Integer getGoal(String Exercise_Name) throws SQLException{
		ResultSet goal = myStmt.executeQuery("SELECT Goal FROM Goal Where Exercise_Name = " + Exercise_Name);
		return goal.getInt("Goal");
	}
	public void getBestResult(String Exercise_Name) throws SQLException{
		
		//NOTE! Might crash at the MAX(a*b).
		ResultSet best = myStmt.executeQuery("SELECT * FROM Workout_Result WHERE Exercise_Name = " + Exercise_Name + " AND MAX(Strain*Repetitions");
		Integer strain = best.getInt("Strain");
		Integer reps = best.getInt("Repetitions");
		String unit = best.getString("Unit");
		Integer total = strain*reps;
		System.out.println("Best result for " + Exercise_Name);
		System.out.println(best.getString("Res_Date")+","+strain+","+best.getString("Unit")+"," + reps + "," + total + "," + (getGoal(Exercise_Name)-total));
	
}
//TODO END GET RESULT/GOAL INFORMATION
//TODO NAVIGATION
	private void workoutCreation() throws SQLException{
		String Buffer = scanner.nextLine();
		
		String returnString = createWorkout();
		SQLUpdate(returnString);
		int pos1 = returnString.indexOf("VALUES");
		int pos2 = returnString.indexOf(",", pos1);
		String Workout_Start = returnString.substring(pos1 + 8, pos2);
		
			System.out.println("Ynskje du å leggja te ein mal for treninga di? (J/N)");
			String ans=scanner.next();
			if(ans.equals("J")||ans.equals("j")){
				System.out.println("kva for ein mal ynskje du å bruka? ");
//				printTemplates(null);
				System.out.print(">");
				Integer num=scanner.nextInt();
//				printTemplates(num); //må lages
//				ArrayList<String> exercises=getExercisesFromTemplate(num);
//				for(int i=0;i<exercises.size();i++){
//					SQLQuery(createWorkoutContains(Workout_Start,exercises.get(i)));
				}
				boolean go;
				go = yesNo();
				while(go){
					printExercises();
					System.out.println("Kva for ein øving ynskje du å leggja te?");
					System.out.print(">");
					ans=scanner.next();
					ans = "'" + ans + "'";
					SQLUpdate(createWorkoutContains(Workout_Start ,ans));
					go = yesNo();
				}
				System.out.println("Følgende trening er nå oppretta: ");
				printWorkout(Workout_Start);
			}
	private boolean yesNo() {
		String ans;
		System.out.println("Ynskje du å leggje te fleire øvingar? (J/N)");
		System.out.print(">");
		ans=scanner.next();
		if(ans.contains("J")||ans.contains("j")||ans.contains("Y")||ans.contains("y")){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * creates a template from user
	 */
	private void template_creation() throws SQLException{ 
		System.out.println("What do you want to name your template? ");
		String templateName=printTemplatesName();
		SQLUpdate(createTemplate(templateName));
		ResultSet rs=SQLQuery("Select last_insert_id from Template");
		int id= rs.getInt(0);
		printExercises();
		boolean go=true;
		while(go){
			System.out.println("Which exercise do you want to add to your template?");
			System.out.print(">");
			String nextExercise=scanner.next();
			if(nextExercise.equals("")){
				go=false;
			}
			else{
				SQLQuery(addToTemplate(id,nextExercise));
			}
		}
		System.out.println("The following template has been created:");
		//printTemplates(id);
		
	}
//TODO END NAVIGATION
//TODO DELETE
	private String DeleteExercise(String Exercise_Name) throws SQLException{
		return "delete * from exercise where Exercise_Name = " + Exercise_Name;
	}
	private String DeleteWorkout(String Workout_Start) throws SQLException{
		return "delete * from Workout where Workout_Start = " + Workout_Start;
	}
	private String DeleteTemplate(String Template_Id) throws SQLException{
		return "delete * from Template where Template_Id = " + Template_Id;
	}
	private String DeleteNote(String Note_Id)throws SQLException{
		return "delete * from Note where Note_Id = " + Note_Id;
	}
	private String DeleteGoal(String goal) throws SQLException{
		return "delete * from Goal where Goal_Id = " + goal;
	}
	private String DeleteGPS(String Gps_Time){
		return "delete * from GPS where GPS_Time = " + Gps_Time;
	}
//TODO END DELETE
//TODO MYSQL QUERY
	public ResultSet SQLQuery(String statement) throws SQLException{
		myStmt = myConn.createStatement();
		ResultSet myRs = myStmt.executeQuery(statement);
		return myRs;
	}
	public Integer SQLUpdate(String statement) throws SQLException{
		myStmt = myConn.createStatement();
		Integer myRs = myStmt.executeUpdate(statement);
		return myRs;
	}
//TODO END MYSQL QUERY
	
}