package commands;

import console_gui.CurrentInformation;

public class CaveinCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"CAVEIN"} ;
    }

    @Override
    public void runCommand(CurrentInformation info, String[] args) {
        boolean error = false;
        
        if (args.length < 2) {
            System.out.print("Not enough arguments, ");
            
            error = true;
        } else {
            switch (args[1].toUpperCase()) {
            case "D":
            case "DOWN":
                if (info.getCurrentRoom().getDown() == null) {
                    System.out.println("There isn't a room there!");
                } else {
                    info.getCurrentRoom().setDown(null);
                    System.out.println("Destroyed Room!");
                }
                break;

            case "U":
            case "UP":
                if (info.getCurrentRoom().getUp() == null) {
                    System.out.println("There isn't a room there!");
                } else {
                    info.getCurrentRoom().setUp(null);
                    System.out.println("Destroyed Room!");              
                }
                break;

            case "N":
            case "NORTH":
                if (info.getCurrentRoom().getNorth() == null) {
                    System.out.println("There isn't a room there!");
                } else {
                    info.getCurrentRoom().setNorth(null);
                    System.out.println("Destroyed Room!");           
                }
                break;

            case "E":
            case "EAST":
                if (info.getCurrentRoom().getEast() == null) {
                    System.out.println("There isn't a room there!");
                } else {
                    info.getCurrentRoom().setEast(null);
                    System.out.println("Destroyed Room!");     
                }
                break;

            case "S":
            case "SOUTH":
                if (info.getCurrentRoom().getSouth() == null) {
                    System.out.println("There isn't a room there!");
                } else {
                    info.getCurrentRoom().setSouth(null);
                    System.out.println("Destroyed Room!");         
                }
                break;

            case "W":
            case "WEST":
                if (info.getCurrentRoom().getWest() == null) {
                    System.out.println("There isn't a room there!");
                } else {
                    info.getCurrentRoom().setWest(null);
                    System.out.println("Destroyed Room!");   
                }
                break;
                
            default:
                System.out.print("Direction not found, ");
                error = true;
            }
        }
        
        if (error) {
            System.out.print("Usage: \"cavein up\"\n");
        }
        
    }

}
