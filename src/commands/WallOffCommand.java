package commands;

import console_gui.UserInformation;

public class WallOffCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"WALLOFF"} ;
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        boolean error = false;
        
        if (args.length != 2) {
            info.out.print("Invalid number of arguments, ");
            
            error = true;
        } else {
            switch (args[1].toUpperCase()) {
            case "D":
            case "DOWN":
                if (info.getCurrentRoom().getDown() == null) {
                    info.out.println("There isn't a room there!");
                } else {
                    info.getCurrentRoom().getDown().setUp(null);
                    info.getCurrentRoom().setDown(null);
                    info.out.println("Blocked Pathway down!");
                }
                break;

            case "U":
            case "UP":
                if (info.getCurrentRoom().getUp() == null) {
                    info.out.println("There isn't a room there!");
                } else {
                    info.getCurrentRoom().getUp().setDown(null);
                    info.getCurrentRoom().setUp(null);
                    info.out.println("Blocked Pathway up!");           
                }
                break;

            case "N":
            case "NORTH":
                if (info.getCurrentRoom().getNorth() == null) {
                    info.out.println("There isn't a room there!");
                } else {
                    info.getCurrentRoom().getNorth().setSouth(null);
                    info.getCurrentRoom().setNorth(null);
                    info.out.println("Blocked Pathway north!");        
                }
                break;

            case "E":
            case "EAST":
                if (info.getCurrentRoom().getEast() == null) {
                    info.out.println("There isn't a room there!");
                } else {
                    info.getCurrentRoom().getEast().setWest(null);
                    info.getCurrentRoom().setEast(null);
                    info.out.println("Blocked Pathway east!"); 
                }
                break;

            case "S":
            case "SOUTH":
                if (info.getCurrentRoom().getSouth() == null) {
                    info.out.println("There isn't a room there!");
                } else {
                    info.getCurrentRoom().getSouth().setNorth(null);
                    info.getCurrentRoom().setSouth(null);
                    info.out.println("Blocked Pathway south!");   
                }
                break;

            case "W":
            case "WEST":
                if (info.getCurrentRoom().getWest() == null) {
                    info.out.println("There isn't a room there!");
                } else {
                    info.getCurrentRoom().getWest().setEast(null);
                    info.getCurrentRoom().setWest(null);
                    info.out.println("Blocked Pathway west!");  
                }
                break;
                
            default:
                info.out.print("Direction not found, ");
                error = true;
            }
        }
        
        if (error) {
            info.out.print("Usage: \"cavein up\"\n");
        }
        
    }

}
