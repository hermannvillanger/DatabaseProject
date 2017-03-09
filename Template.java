package database;

public class Template {

	
	public static String createTemplate(String Template_Name, Integer Template_Id){
		return "INSERT INTO Template (Template_Name,Template_Id)" +
				"VALUES (" + Template_Name + "," + Template_Id +")";
	}
	public static String addToTemplate(String Exercise_Name, Integer Template_Id){
		return "INSERT INTO Template (Exercise_Name,Template_Id)" +
				"VALUES (" + Exercise_Name + "," + Template_Id + ")";
	}
	
}
