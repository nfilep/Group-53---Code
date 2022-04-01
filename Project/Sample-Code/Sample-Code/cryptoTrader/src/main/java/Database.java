package Database;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Database {
    // TODO: remove and replace with ewere's User class 
    private static class User {
        int id;
        String username, password;

        User(int id, String username, String password) {
            this.id = id;
            this.username = username;
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

    }

    private static Map<Integer, User> userMap = new HashMap<Integer, User>();

    public static void interpretDataFile(String fileName) {
        try {
            File userFile = new File(fileName);
            Scanner reader = new Scanner(userFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] line = data.split(" ");
                int id = Integer.valueOf(line[0]);
                String username = line[1];
                String password = line[2];
                User newUser = new User(id, username, password);
                userMap.put(id, newUser);
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Could not find a data file named " + "'" + fileName + "'");
            e.printStackTrace();
        }
    }

    public static boolean lookupUsername(Integer userId) {
        return userMap.containsKey(userId);
    }

    public static boolean verifyPassword(Integer userId, String password) {
        User user = userMap.get(userId);
        return user.getPassword() == password;
    }

}
