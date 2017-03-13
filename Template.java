package database;

import java.util.Scanner;

public class Template {
	static Scanner scanner;
	
	public static String createTemplate(){
		System.out.println("Create Template");
		System.out.println("Template Name:");
		System.out.println(">");
		String Template_Name = scanner.nextLine();
System.out.println("Template Id:");
System.out.println(">");
String Template_Id = scanner.nextLine();
		
		return "INSERT INTO Template (Template_Name,Template_Id)" +
				"VALUES (" + Template_Name + "," + Template_Id +")";
	}
	public static String addToTemplate(){
		System.out.println("Add To Template");
		System.out.println("Exercise Name:");
		System.out.println(">");
		String Exercise_Name = scanner.nextLine();
System.out.println("Template Id:");
System.out.println(">");
String Template_Id = scanner.nextLine();

		return "INSERT INTO Template (Exercise_Name,Template_Id)" +
				"VALUES (" + Exercise_Name + "," + Template_Id + ")";
	}
	
}
