package commands;

import console_gui.CurrentInformation;
import model.RoomNode;

public class DigCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"DIG"} ;
    }

    @Override
    public void runCommand(CurrentInformation info, String[] args) {
        boolean error = false;
        
        if (args.length < 3) {
            info.out.print("Not enough arguments, ");
            
            error = true;
        } else {
            StringBuilder description = new StringBuilder();
            
            for (int i = 2; i < args.length; i++) {
                description.append(args[i]);
                description.append(' ');
            }

            switch (args[1].toUpperCase()) {
            case "D":
            case "DOWN":
                if (info.getCurrentRoom().getDown() != null) {
                    info.out.println("There is already a room there!");
                } else {
                    RoomNode r = new RoomNode(info.rooms.getUniqueRoomID(), description.toString());
                    info.getCurrentRoom().setDown(r);
                    r.setUp(info.getCurrentRoom());
                    info.out.println("Created Room!");
                }
                break;

            case "U":
            case "UP":
                if (info.getCurrentRoom().getUp() != null) {
                    info.out.println("There is already a room there!");
                } else {
                    RoomNode r = new RoomNode(info.rooms.getUniqueRoomID(), description.toString());
                    info.getCurrentRoom().setUp(r);
                    r.setDown(info.getCurrentRoom());  
                    info.out.println("Created Room!");                
                }
                break;

            case "N":
            case "NORTH":
                if (info.getCurrentRoom().getNorth() != null) {
                    info.out.println("There is already a room there!");
                } else {
                    RoomNode r = new RoomNode(info.rooms.getUniqueRoomID(), description.toString());
                    info.getCurrentRoom().setNorth(r);
                    r.setSouth(info.getCurrentRoom());      
                    info.out.println("Created Room!");              
                }
                break;

            case "E":
            case "EAST":
                if (info.getCurrentRoom().getEast() != null) {
                    info.out.println("There is already a room there!");
                } else {
                    RoomNode r = new RoomNode(info.rooms.getUniqueRoomID(), description.toString());
                    info.getCurrentRoom().setEast(r);
                    r.setWest(info.getCurrentRoom());              
                    info.out.println("Created Room!");      
                }
                break;

            case "S":
            case "SOUTH":
                if (info.getCurrentRoom().getSouth() != null) {
                    info.out.println("There is already a room there!");
                } else {
                    RoomNode r = new RoomNode(info.rooms.getUniqueRoomID(), description.toString());
                    info.getCurrentRoom().setSouth(r);
                    r.setNorth(info.getCurrentRoom());        
                    info.out.println("Created Room!");            
                }
                break;

            case "W":
            case "WEST":
                if (info.getCurrentRoom().getWest() != null) {
                    info.out.println("There is already a room there!");
                } else {
                    RoomNode r = new RoomNode(info.rooms.getUniqueRoomID(), description.toString());
                    info.getCurrentRoom().setWest(r);
                    r.setEast(info.getCurrentRoom());         
                    info.out.println("Created Room!");      
                }
                break;
                
            default:
                info.out.print("Direction not found, ");
                error = true;
            }
            
        }
        
        if (error) {
            info.out.print("Usage: \"dig up A Vast Sky: a wonderful view of the forest below.\"\n");
        }
    }

}
