package Database;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import User.User;

/**
 * This class stores the user's login data
 * @author anushaiyengar
 * @version 1.2
 * @date 2022-0-31 
 *
 */
public class Database {

	/**
	 * instance      
	 * userMap
	 * dataFile
	 */
	private static Database instance;
	private static Map<String, User> userMap = new HashMap<String, User>();
	private static String dataFile; 

	/**
	 * the constructor
	 * @param dataFile		textfile containing data entries
	 */
	private Database(String dataFile){
		this.dataFile = dataFile;
		instance.interpretDataFile(dataFile);
	}

	/**
	 * 
	 * @param dataFile
	 * @return
	 */
	public static Database getInstance(String dataFile){
		if(instance == null){
			instance = new Database(dataFile); 
		}
		return instance;
	}


	/**
	 * Reads the text file containing data and stores 
	 * the data entries(username and password) in a data structure 
	 * @param fileName 	the name of the text file containing the date
	 * @exception FileNotFoundException
	 */
	public static void interpretDataFile(String fileName) {
		try {
			File userFile = new File(fileName);
			Scanner reader = new Scanner(userFile);
			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				String[] line = data.split(" ");
				String username = line[0];
				String password = line[1];
				User newUser = new User(username, password);
				userMap.put(username, newUser);
			}
			reader.close();

		} catch (FileNotFoundException e) {
			System.out.println("Could not find a data file named " + "'" + fileName + "'");
			e.printStackTrace();
		}
	}
	/**
	 * This method searches for the users username in the system
	 * @param username  a String representing the name of the user
	 * @return        	true if the username exists, false otherwise 
	 */
	public static boolean lookupUsername(String username) {
		return userMap.containsKey(username);
	}

	/**
	 * This method is used for verifying if the password matches 
	 * the username in the database
	 * @param username  a String representing the name of the user
	 * @param password  a String containing the password of a user
	 * @return true if the user's password matches their username, otherwise false
	 */
	public static boolean verifyPassword(String username, String password) {
		User user = userMap.get(username);
		return user.getPassword().equals(password);
	}

}
