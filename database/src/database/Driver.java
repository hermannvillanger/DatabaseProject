package database;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 				System.out.println("4: Add a result to your workout");
 				System.out.println("5: Add goal to exercise");
 				System.out.println("6: Register Finished Workout");
 				System.out.println("7: Review your goals");

 				System.out.println("10: Exit");
 				Integer num=scanner.nextInt();
 				switch (num){
 				case 1: templateCreation();
				break;
 				case 2: exerciseCreation();
 				break;
 				case 3: workoutCreation();
 				break;
 				case 4: addResult();
 				break;
 				case 5: registerGoal();
 				break;
 				case 6: finishedWorkout();
 				break;
 				case 7:printExercisesWithGoalTime();
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
	public String createExercise(String Exercise_Name, String Description,String Unit){
		return "INSERT INTO Exercise (Exercise_Name,Description, Unit) " +
		"\nVALUES (" + Exercise_Name + "," + Description + "," + Unit + ")";
	}
	public String createExerciseGroup(){
		System.out.println("Create Exercise Group");
		String Group_Name = getGroupName();
		String Description = getDescription();
		
		return "INSERT INTO Exercise_Group (Group_Name,Description) " +
		"\nVALUES (" + Group_Name + "," + Description +")";
	}
	public String createExerciseResult(String exercise_name,String workout_start) throws SQLException{
		System.out.println("Exercise Result");
		Integer Strain = Integer.valueOf(getStrain());
		Integer Repetitions = Integer.valueOf(getRepetitions());
		
		return "INSERT INTO Workout_Result (Strain,Repetitions,Exercise_Name,Workout_Start) " +
		"\nVALUES (" + Strain + "," + Repetitions + "," + exercise_name + "," + workout_start + ")";
	}
	public String createGoal(){
		System.out.println("Create Goal");
		System.out.println("Here's a list over available Exercises:");
		try {
			printExercises();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Which Exercise do you wish to add a Goal for?");
		String Exercise_Name = getExerciseName();
		String Goal = getGoal();
		String Start_Date = getSpecificDate("Start");
		String End_Date = getSpecificDate("End");
		
		
		return "INSERT INTO Goal (Goal,Start_Date,End_Date,Exercise_Name) " +
		"VALUES (" + Goal + "," + Start_Date + "," + End_Date + "," + Exercise_Name + ")";
	}
	public String createGoal(String Exercise_Name){
		System.out.println("Create Goal for " + Exercise_Name);
		
		String Goal = getGoal();
		String Start_Date = getSpecificDate("Start");
		String End_Date = getSpecificDate("End");
		
		
		return "INSERT INTO Goal (Goal,Start_Date,End_Date,Exercise_Name) " +
		"VALUES (" + Goal + "," + Start_Date + "," + End_Date + "," + Exercise_Name + ")";
	}
	public String insertExerciseInGroup(){
		System.out.println("Insert Exercise into Group");
		String Exercise_Name = getExerciseName();
		String Group_Name = getGroupName();
		
		return "INSERT INTO Exercise_In_Group (Exercise_Name,Group_Name) " +
		"\nVALUES (" + Exercise_Name + "," + Group_Name + ")";
	}
	public String insertExerciseInGroup(String Exercise_Name, String Group_Name){

		return "INSERT INTO Exercise_In_Group (Exercise_Name,Group_Name) " +
		"\nVALUES (" + Exercise_Name + "," + Group_Name + ")";
	}
	public String createNote(String Workout_Start){
		System.out.println("Create Note");
		String Purpose = getPurpose();
		String Tips = getTips();
		
		return "INSERT INTO Note (Workout_Start,Purpose,Tips) " +
		"\nVALUES (" + Workout_Start + "," + Purpose + "," + Tips +")";
	}
	public String createWorkout(){
		scanner.nextLine();
		System.out.println("Create Workout");
		String Workout_Start = getWorkoutStart();
		String Workout_End = getWorkoutEnd();
		
		return "INSERT INTO Workout (Workout_Start,Workout_End) " +
		"\nVALUES (" + Workout_Start + "," + Workout_End + ")";
	}
	public String createWorkoutContains(String Workout_Start,String Exercise_Name){		
		return "INSERT INTO Workout_Contains (Workout_Start,Exercise_Name) " +
		"\nVALUES (" + Workout_Start  + "," + Exercise_Name +")";
	}
	public String createTemplate(String Template_Name){
		return "INSERT INTO Template (Template_Name) " +
		"\nVALUES (" + Template_Name + ")";
	}
	public String addToTemplate(int Template_Id,String Exercise_Name){
		return "INSERT INTO Template_Contains (Exercise_Name,Template_Id) " +
		"\nVALUES (" + Exercise_Name + "," + Template_Id + ")";
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
	private String getSpecificDate(String date) {
		System.out.println(date + " Date: 'YYYY-MM-DD'");
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
		System.out.println("Goal (As Strain * Repetitions[min:1]) :");
		System.out.print(">");
		String goal = scanner.nextLine();
		return goal;
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
	private String getTemplateId() {
		System.out.println("Which Template do you want to use?");
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
	private String getTemplateName() {
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
/*TODO TIL MULIG FORENKLING AV KODEN
	private String getInteger(String What) {
		System.out.println(What + ":(Integer)");
		System.out.print(">");
		What = scanner.nextLine();
		return What;
	}
	private String getString(String What) {
	System.out.println(What + ": (String)");
	System.out.print(">");
	What = "'"  + scanner.nextLine() + "'" ;
	return What;

}*/

//TODO END GET FROM USER
//TODO PRINT
	public void printExercises() throws SQLException{
		myStmt = myConn.createStatement();
		
		ResultSet myRs = myStmt.executeQuery("select * from Exercise");
		if(myRs.isBeforeFirst()){
			System.out.println("Exercises:");
			while(myRs.next()){
				System.out.println(myRs.getString("Exercise_Name") + ": " + myRs.getString("Description"));
			}
		}
		else{
			System.out.println("There are no Exercises");
		}
	}	
	private void printGoalsFromExercise(String exercise) throws SQLException{
		ResultSet rs=SQLQuery("select goal_id,goal from Goal where Exercise_Name = " + exercise);
		while(rs.next()){
			System.out.println(rs.getString(1) + ": " + rs.getString(2));
		}
	}
	public void printWorkout(String Workout_Start) throws SQLException{
		myStmt = myConn.createStatement();
		
		ResultSet myRs = myStmt.executeQuery("select * from Workout where Workout.Workout_Start = " +  Workout_Start);
		System.out.println("Workout_Start, Workout_End, Shape, Performance, Template_Id, Climate");
		if(myRs.isBeforeFirst()){
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
		else{
			System.out.println("The Workout does not exist");
		}
	}
	public void printWorkouts() throws SQLException{
		myStmt = myConn.createStatement();
		
		ResultSet myRs = myStmt.executeQuery("select Workout_Start, Workout_End,Template_Id from Workout");
		if(myRs.isBeforeFirst()){
			System.out.println("Workout_Start, Workout_End, Template_Id");
			while(myRs.next()){
				System.out.println( myRs.getString("Workout_Start") + "," + myRs.getString("Workout_End") + 
						"," + myRs.getString("Template_Id"));
			}
		}
		else{
			System.out.println("There are no saved Workouts");
		}
	}
	public void printExerciseGroups() throws SQLException{
//Prints all groups
		myStmt = myConn.createStatement();
		
		ResultSet myRs = myStmt.executeQuery("select * from Exercise_Group");
		if(myRs.isBeforeFirst()){
			System.out.println("Exercise Groups:");
			while(myRs.next()){
				System.out.println(myRs.getString("Group_Name") + ": " + myRs.getString("Description"));
			}
		}
		else{
			System.out.println("There are no saved Exercise Groups");
		}
	}
	public void printExercisesInGroup(String Group_Name) throws SQLException{
//Prints one group, with all exercises
		myStmt = myConn.createStatement();
		ResultSet myRs = myStmt.executeQuery("select * from exercise_group where group_name = " + Group_Name);
		if(myRs.isBeforeFirst()){
			System.out.println("Exercise Group:");
			myRs.next();
			System.out.println(myRs.getString("Group_Name") + ": " + myRs.getString("Description"));
			ResultSet exRs = SQLQuery("select * from exercise_group natural join "
					+ "exercise_in_group join exercise on exercise.Exercise_Name = "
					+ "exercise_in_group.Exercise_Name where group_name = " + Group_Name);
			if(exRs.isBeforeFirst()){
				System.out.println("Exercise Name: Description");
				while(myRs.next()){
					System.out.println(myRs.getString("Exercise_Name") + ": " + myRs.getString("Description"));
				}
			}
			else{
				System.out.println("The Exercise Group does not have any exercises");
			}
		}
		else{
			System.out.println("The Exercise Group does not exist");
		}
	
	}
	public void printTemplates(Integer Template_Id) throws SQLException{
		if (Template_Id == null) {
			myStmt = myConn.createStatement();
			
			ResultSet myRs = myStmt.executeQuery("select * from Template");
			if(myRs.isBeforeFirst()){
				System.out.println("Templates:");
				while(myRs.next()){
					int Id = myRs.getInt("Template_Id");
					System.out.println(Id + ": " + myRs.getString("Template_Name"));
					ResultSet a = SQLQuery("select Exercise_Name from template_contains where template_Id = " + Id);
					if(a.isBeforeFirst()){
						while (a.next()) {
							String exercise=a.getString("Exercise_Name");
							System.out.println(exercise);
						}
					}
					else{
						System.out.println("The Template does not have any Exercises");
					}
				}
			}
			else{
				System.out.println("There are no saved Templates");
			}
		}
		else {
			myStmt = myConn.createStatement();
			
			ResultSet myRs = myStmt.executeQuery("select * from Template Where Template_Id = " + Template_Id);
			if(myRs.isBeforeFirst()){
				
				System.out.println("Template:");
				myRs.next();
				System.out.println(myRs.getString("Template_Id") + ": " + myRs.getString("Template_Name"));
				ResultSet a = SQLQuery("select Exercise_Name from template_contains where template_Id = " + Template_Id);
				if(myRs.isBeforeFirst()){
					while (a.next()) {
						String exercise=a.getString("Exercise_Name");
						System.out.println(exercise);
					}
				}
				else{
					System.out.println("The Template does not have any Exercises");
				}
			}
			else{
				System.out.println("The Template does not exist");
			}
		}
	}

	private void printExercisesWithGoalTime() throws SQLException, ParseException{
		boolean valid=false;
		String exercise="";
		while(!valid){
			System.out.println("Which exercise do you wish to review your goals for?");
			printExercises();
			System.out.print(">");
			exercise=scanner.nextLine();
			exercise="'" + exercise + "'";
			ResultSet rs1= SQLQuery("SELECT Exercise_Name FROM Exercise WHERE exercise_name = "+ exercise);
			if(rs1.first()){
				valid=true;
			}
			else{
				System.out.println("This exercise does not exist yet. Please enter a valid exercise name");
			}
		}
		System.out.println("Which goal do you wish to review your results for? (goal #)");
		printGoalsFromExercise(exercise);
		System.out.print(">");
		Integer goal=scanner.nextInt();
		ResultSet rs2=SQLQuery("Select Start_date,End_date from goal where goal_id = " + goal);
		if(rs2.first()){
			String start=rs2.getString(1);
			String end=rs2.getString(2);
			getResults(exercise,start,end);
		}
	}
	public ArrayList<String> getTemplatesArray(Integer Template_Id) throws SQLException {
		myStmt = myConn.createStatement();
		ArrayList<String> tempArr= new ArrayList<String>();
		
		ResultSet a = SQLQuery("select Exercise_Name from template_contains where template_Id = " + Template_Id);
		while (a.next()) {
			String exercise=a.getString("Exercise_Name");
			// System.out.println(exercise);
			tempArr.add("'" + exercise + "'");
		}
		return tempArr;
	}
//TODO END PRINT
//TODO GET RESULT/GOAL INFORMATION
	public void getResults(String Exercise_Name, String start, String end) throws SQLException, ParseException{
		DateFormat formatter=new SimpleDateFormat("yyyy-mm-dd");
		Date startDate=(Date) formatter.parse(start);
		Date endDate=(Date) formatter.parse(end);
		String statement="select repetitions,strain,unit from Workout_result";
		if(start!=null || end!=null){
			statement+=" where";
		}
		if(start!=null){
			statement+=" Workout_start >= ' "+ startDate + " 00:00:00'";
		}
		if(start!=null && end!=null){
			statement+=" and";
		}
		if(end!=null){
			statement+="Workout_start <= '" + endDate + " 23:59:59'";
		}
		ResultSet rs=SQLQuery(statement);
		while(rs.next()){
			System.out.println(rs.getString(1) + "|" + rs.getString(2) + "|" + rs.getString(3));
		}
	}
	
	public Integer getGoal(String Exercise_Name) throws SQLException{
		ResultSet goal = myStmt.executeQuery("SELECT Goal FROM Goal Where Exercise_Name = " + Exercise_Name);
		if (goal.next()){
			return goal.getInt("Goal");
			}
			else{
				return null;
			}
	}
	public void getBestResult(String Exercise_Name) throws SQLException{
		
		//NOTE! Might crash at the MAX(a*b).
		ResultSet best = myStmt.executeQuery("SELECT * FROM Workout_Result WHERE Exercise_Name = " + Exercise_Name + " AND MAX(Strain*Repetitions");
		best.next();
		Integer strain = best.getInt("Strain");
		Integer reps = best.getInt("Repetitions");
		String unit = best.getString("Unit");
		Integer total = strain*reps;
		System.out.println("Best result for " + Exercise_Name);
		if(getGoal(Exercise_Name)==null){
			System.out.println(best.getString("Res_Date")+","+strain+","+best.getString("Unit")+"," + reps + "," + total + ",No goal set");

		}
		else{
		System.out.println(best.getString("Res_Date")+","+strain+","+best.getString("Unit")+"," + reps + "," + total + "," + (getGoal(Exercise_Name)-total));
		}
}
//TODO END GET RESULT/GOAL INFORMATION
//TODO NAVIGATION
	private void workoutCreation() throws SQLException{
		
		String returnString = createWorkout();
		SQLUpdate(returnString);
		int pos1 = returnString.indexOf("VALUES");
		int pos2 = returnString.indexOf(",", pos1);
		String Workout_Start = returnString.substring(pos1 + 8, pos2);
		
			System.out.println("Do you want to add a template for your workout? (Y/N)");
			System.out.print(">");
			String ans=scanner.nextLine();
			if(ans.equals("J")||ans.equals("j")||ans.equals("Y")||ans.equals("y")){
				printTemplates(null);
				Integer num = Integer.valueOf(getTemplateId());
				printTemplates(num);
				
				String update = "Update Workout Set Template_Id = "+ num + 
						"\n Where Workout_Start = " + Workout_Start;
				SQLUpdate(update);
				ArrayList<String> exercises = getTemplatesArray(num);
				for(int i=0;i<exercises.size();i++){
					SQLUpdate(createWorkoutContains(Workout_Start,exercises.get(i)));
				}
				boolean go;
				go = yesNo("Workouts");
				while(go){
					printExercises();
					ans = getExerciseName();
					SQLUpdate(createWorkoutContains(Workout_Start ,ans));
					go = yesNo("Workouts");
				}
				System.out.println("The following workout has been created: ");
				printWorkout(Workout_Start);
			}
		}
	private void exerciseCreation() throws SQLException{
		scanner.nextLine();
		
		String ans;
		String description;
		String groupAns;
		
//		TODO: Ask for unit: which unit will the strain be in
		boolean go = true;
		while(go){
			ArrayList<String> groups = new ArrayList<>();
			ans = getExerciseName();
			
			System.out.println("Add a description to " + ans + " (max 140 characters)");
			System.out.print(">");
			description=scanner.nextLine();
			description = "'" + description + "'";
			System.out.println("What unit will the exercise be recorded in? (kg, km, m, minutes...)");
			System.out.print(">");
			String unit = "'" + scanner.nextLine() + "'";			
			
			boolean addGroups = true;
			while(addGroups){
				System.out.println("What Exercise Group(s) does " + ans + " belong to?");
				System.out.println("If your Exercise Group does not exist in the list, write 'new'");
				printExerciseGroups();
				System.out.print(">");
				groupAns = scanner.nextLine();
				
				if(groupAns.equalsIgnoreCase("new")){
					String newGroup = createExerciseGroup();
					SQLUpdate(newGroup);
					int pos1 = newGroup.indexOf("VALUES");
					int pos2 = newGroup.indexOf(",", pos1);
					String Group_Name = newGroup.substring(pos1 + 8, pos2);
					groups.add(Group_Name);
					System.out.println("You have added " + ans + " into the new group " + Group_Name);
					addGroups = yesNo("Exercise Groups to " + ans);
				}else{
					groupAns = "'" + groupAns + "'";
					ResultSet exists = SQLQuery("SELECT Group_Name FROM Exercise_Group WHERE Group_Name = "+ groupAns);
					if(exists.first()){
						groups.add(groupAns);
						System.out.println("You have added " + ans + " into the Exercise Group " + groupAns );
						addGroups = yesNo("Exercise Groups to "+ans);
					}else{
						System.out.println("This Exercise Group does not exist, make sure you look for typos. Add new Group by writing 'new'");
					}
				}
			}
			SQLUpdate(createExercise(ans, description, unit));
			for(int i=0; i<groups.size();i++){
				SQLUpdate(insertExerciseInGroup(ans, groups.get(i)));
			}
			System.out.println("You have now created the Exercise: " + ans);
			System.out.println(ans + " has been added into the following groups:");
			System.out.println(groups);
			String goalReg;
			System.out.println("Do you wish to add a goal for " + ans + "?(Y/N)");
			System.out.print(">");
			goalReg = scanner.nextLine();
			if(goalReg.contains("J")||goalReg.contains("j")||goalReg.contains("Y")||goalReg.contains("y")){
				SQLUpdate(createGoal(ans));
			}
			go = yesNo("Exercises");
		}
	}
	private void exerciseGroupCreation(){
		
		boolean addMoreGroups = true;
		String groupName;
		String description;
		String exerciseAns;
		
		
		while(addMoreGroups){
			groupName = getGroupName();
			ResultSet exists = SQLQuery("SELECT Group_Name FROM Exercise_Group WHERE Group_Name = "+ groupName);
			if(exists.first()){
				System.out.println("A group named " + groupName + " already exists:");
				addExercisesToGroup(groupName); //TODO
			}
			else{
				System.out.println("Add a description to " + groupName + " (max 140 characters)");
				System.out.print(">");
				description=scanner.nextLine();
				description = "'" + description + "'";
			
				String Group_Name = createExerciseGroup(groupName, description);
				SQLUpdate(Group_Name);
				addExercisesToGroup(groupName);
			}
			
			
			addMoreGroups = yesNo("Exercise Groups");
		}
		
		
	}
	/**
	 * Dialogue for adding between 0 and n exercises to a specified Exercise Group
	 */
	private void addExercisesToGroup(String Group_Name){	
		boolean addMoreExer;
		String exerAns;
		System.out.println("Currently these Exercises are added to " + Group_Name);
		printExercisesInGroup(Group_Name);

		addMoreExer = yesNo("Exercises to " + Group_Name);
		
		String exerciseName;
		String description;
		
		while(addMoreExer){
			System.out.println("What Exercise do you wish to add to " + Group_Name + "?");
			System.out.println("If your Exercise does not exist in the list, write 'new'");
			printExercises();
			System.out.print(">");
			exerAns = scanner.nextLine();
			
			if(exerAns.equalsIgnoreCase("new")){
				String newExercise = createExercise();
				SQLUpdate(newExercise);
				int pos1 = newExercise.indexOf("VALUES");
				int pos2 = newExercise.indexOf(",", pos1);
				String Exercise_Name = newExercise.substring(pos1 + 8, pos2);
				SQLUpdate(insertExerciseInGroup(Exercise_Name, Group_Name));
				System.out.println("You have added the new Exercise " + Exercise_Name + " into the Exercise Group " + Group_Name);
				System.out.println("Currently these Exercises are added to " + Group_Name);
				printExercisesInGroup(Group_Name);
				addMoreExer = yesNo("Exercises to " + Group_Name);
			}else{
				exerAns = "'" + exerAns + "'";
				ResultSet exists = SQLQuery("SELECT Exercise_Name FROM Exercise WHERE Exercise_Name = "+ exerAns);
				if(exists.first()){
					SQLUpdate(insertExerciseInGroup(exerAns, Group_Name));
					System.out.println("You have added " + exerAns + " into the Exercise Group " + groupAns );
					addMoreExer = yesNo("Exercises to "+Group_Name);
				}else{
					System.out.println("This Exercise does not exist, make sure you look for typos. Add new Exercise by writing 'new'");
				}
			}
		}
	}
	private void registerGoal() throws SQLException{
		scanner.nextLine();
		boolean go = true;
		while(go){
			SQLUpdate(createGoal());
			go=yesNo("Goals");
		}
	}
	private boolean yesNo(String What) {
		String ans;
	
		while(true){
			
			System.out.println("Do you wish to add more " + What + "? (Y/N)");
			System.out.print(">");
			ans = scanner.nextLine();
			if(ans.contains("J")||ans.contains("j")||ans.contains("Y")||ans.contains("y")){
				return true;
			}else if(ans.contains("N")||ans.contains("n")||ans.contains("No")||ans.contains("no")){
				return false;
			}else{
				System.out.println("Invalid input, please check for typos");
			}
		}
		
	}
	private void finishedWorkout() throws SQLException{
		System.out.println("Which workout did you do?");
		printWorkouts();
		scanner.nextLine();
		String Workout_Start = getWorkoutStart();
		System.out.println("How was you experience of the workout?");
		String Shape = getShape();
		String Performance = getPerformance();
		String Climate = getClimate();
		String update = "Update Workout \nSet Shape = " + Shape +
				", Performance = " + Performance + ", Climate = "+ Climate + 
				"\n where Workout_Start = " + Workout_Start;
		SQLUpdate(update);
		
		boolean go = yesNo("Notes");
		while(go){
			String note = createNote(Workout_Start);
			SQLUpdate(note);
			go = yesNo("Notes");
		}
	}
	/**
	 * creates a template from user
	 */
	private void templateCreation() throws SQLException{ 
		scanner.nextLine();
		System.out.println("What do you want to name your template? ");
		String templateName = getTemplateName();
		SQLUpdate(createTemplate(templateName));
		ResultSet rs = SQLQuery("Select last_insert_id() from Template");
		rs.next();
		Integer id= Integer.valueOf(rs.getString(1));
		
		printExercises();
		boolean go=true;
		while(go){
			System.out.println("Which exercise do you want to add to your template?(Press N to exit)");
			String nextExercise = getExerciseName();
			if(nextExercise.equals("'N'")||nextExercise.equals("'n'")){
				go=false;
			}
			else{
				SQLUpdate(addToTemplate(id,nextExercise));
			}
		}

		System.out.println("The following template has been created:");
		printTemplates(id);
		
	}
	private void addResult() throws SQLException{
		System.out.println("Which workout do you wish to edit your results for?");
		printWorkouts();
		scanner.nextLine();
		String workout = getWorkoutStart();
		boolean go = true;;
		String exercise;
		String statement;
		while(go){
			System.out.println("which exercise do you wish to add a result to?");
			ResultSet b =SQLQuery("Select Exercise_name from Workout_Contains where workout_start = " + workout);
			while(b.next()){
				System.out.println(b.getString(1));
			}
			exercise = getExerciseName();
			statement = createExerciseResult(exercise,workout);
			
			SQLUpdate(statement);
			go = yesNo("Results");
		}
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