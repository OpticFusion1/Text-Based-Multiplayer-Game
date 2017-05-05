package console_gui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commands.Command;
import commands.QuitCommand;
import model.RoomManager;
import model.SerializationHelper;
import model.UserSave;

public class Console {
    
    private static final Pattern WHITE_SPACE = Pattern.compile("\\s");
    
    public static void main(String[] args) {
        RoomManager rm =  SerializationHelper.loadRoomManager();
        
        if (rm == null) {
            System.err.println("Failed to Load System!");
            return;
        }
        
        UserInputScanner input = new UserInputScanner(System.in);
        UserInformation info = new UserInformation(rm, System.out, input);
        
        int fails = 0;
        
        while (login(info) && fails < 3) fails++;
        
        if (fails >= 3) {
            info.out.println("Failed to login, now exiting.");
            return;
        }
        
        
        info.out.println(info.getCurrentRoom().getDescription());
        
        Command com = input.getNextCommand();
        
        while (!(com.getRunnable() instanceof QuitCommand)) {
            if (com != null) {
                com.run(info);
            }
            
            com = input.getNextCommand();
        }

        info.out.println("Thank you for experiencing Node Traversing Simulator 2017");
        
        SerializationHelper.saveUser(new UserSave(info.getCurrentRoom().getRoomID(), info.getUsername()));
        SerializationHelper.saveRoomManager(rm);
    }
    
    /**
     * Attempts to log the user in.
     * @param info the user we will be talking to.
     * @return if the user was able to log in.
     */
    public static boolean login(UserInformation info) {
        boolean result;
        
        info.out.print("Please Enter Your Username: ");
        
        
        String line = info.input.nextLine();
        Matcher matches = WHITE_SPACE.matcher(line);
        
        if (matches.find()) {
            info.out.println("Error, usernames cannot contain whitespace!");
            result = false;
        } else if (!SerializationHelper.userExists(line)) {
            info.out.println("Unable to find user, would you like to create a new save? (YES/NO): ");
            line = info.input.nextLine().toUpperCase();
            
            if (line.equals("YES") || line.equals("Y")) {
                info.setUsername(line);
                result = true;
            } else {
                result = false;
            }
        } else {
            UserSave save = SerializationHelper.loadUser(line);
            info.setUsername(save.username);
            result = true;
        }
        
        return result;
    }
}
