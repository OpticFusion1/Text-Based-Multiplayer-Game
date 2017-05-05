package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A class to help with saving and loading files.
 * 
 *  @author Zachary Chandler
 */
public class SerializationHelper {
    /** The folder all data will be saved in. */
    private static final String DATA_FOLDER = "data/";
    
    /** The sub-folder that all users will be stored in. */
    private static final String USERS_FOLDER = "users/";
    
    /** The name of the file storing all of the rooms. */
    private static final String ROOM_MANAGER_FILE = "rooms.ser";
    
    /** The folders that lead to where user data will be saved. */
    private static final String ROOM_MANAGER_SAVE = DATA_FOLDER + ROOM_MANAGER_FILE;

    /**
     * Load a given user's save.
     * @param username the user name the file is saved under.
     * @return a users save object.
     */
    public static UserSave loadUser(String username) {
        return (UserSave) loadObject(DATA_FOLDER + USERS_FOLDER + username + ".ser");
    }
    
    /**
     * Saves a users information.
     * @param user the save object that will be stored.
     * @return if the file was successfully saved.
     */
    public static boolean saveUser(UserSave user) {
        return saveObject(user, DATA_FOLDER + USERS_FOLDER + user.username + ".ser"); 
    }
    
    /**
     * Check if a given user save file exists.
     * @param username the user name to check.
     * @return if there is a save file with the given user name.
     */
    public static boolean userExists(String username) {
        File userFile = new File(DATA_FOLDER + USERS_FOLDER + username + ".ser");
        return userFile.exists();
    }
    
    /**
     * @return the room manager.
     */
    public static RoomManager loadRoomManager() {
        return (RoomManager) loadObject(ROOM_MANAGER_SAVE);
    }
    
    /**
     * @param rm the room manager to save.
     * @return if the save was successful.
     */
    public static boolean saveRoomManager(RoomManager rm) {
        return saveObject(rm, ROOM_MANAGER_SAVE);
    }
    
    /**
     * Load an object.
     * @param fileName the file path to the object
     * @return the object that was loaded.
     */
    public static Object loadObject(String fileName) {
        Object result;
        
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            result = in.readObject();
            in.close();
            fileIn.close();
            //System.out.println("Loaded " + fileName);
        }catch(IOException i) {
            System.err.println(i.getMessage());
            System.err.println("FAILED to load " + fileName);
            return null;
        }catch(ClassNotFoundException c) {
            System.err.println(c.getMessage());
            System.err.println("FAILED to load " + fileName + ", class not found");
            return null;
        }
        
        return result;
    }
    
    /**
     * Save an object.
     * @param s the object to save
     * @param fileName the file path to the save location.
     * @return if the object was successfully saved.
     */
    public static boolean saveObject(Object s, String fileName) {
        boolean result = true;
        
        try {
             FileOutputStream fileOut =
             new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut);
             out.writeObject(s);
             out.close();
             fileOut.close();
             //System.out.println("Saved system to " + fileName);
          } catch(IOException i) {
              System.err.println(i.getMessage());
              System.err.println("FAILED to Save system to " + fileName);
              result = false;
          }
        
        return result;
    }
}
