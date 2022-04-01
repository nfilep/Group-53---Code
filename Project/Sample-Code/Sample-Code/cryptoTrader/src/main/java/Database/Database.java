package Database;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Database {

    private static Database instance = null;
    private static String dataFile; 

    private Database(String dataFile){
        this.dataFile = dataFile; 
        
    }

    private static Database getInstance(){
        if(instance == null){
            instance = new Database(dataFile); 
        }
        return instance;
    }

    // TODO: remove and replace with Ewere's User class 
    private static class User {
        String username, password;

        User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public String getUsername() {
            return username;
        }

    }

    private static Map<String, User> userMap = new HashMap<String, User>();

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

    public static boolean lookupUsername(String username) {
        return userMap.containsKey(username);
    }

    public static boolean verifyPassword(String username, String password) {
        User user = userMap.get(username);
        return user.getPassword().equals(password);
    }

}
