package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SerializationHelper {
    private static final String DATA_FOLDER = "data";
    private static final String SAVE_FILE = "quick_save.ser";
    
    public static final Path QUICK_SAVE_LOCATION = Paths.get(DATA_FOLDER, SAVE_FILE);
    
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
