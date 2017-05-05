package commands;

import console_gui.UserInformation;
import model.RoomNode;

public class DigCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"DIG"} ;
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
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
            
            RoomNode room = null;
            
            switch (args[1].toUpperCase()) {
            case "D":
            case "DOWN":
                if (info.getCurrentRoom().getDown() != null) {
                    info.out.println("There is already a room there!");
                } else {
                    room = new RoomNode(info.rooms.getUniqueRoomID(), description.toString());
                    info.getCurrentRoom().setDown(room);
                    room.setUp(info.getCurrentRoom());
                }
                break;

            case "U":
            case "UP":
                if (info.getCurrentRoom().getUp() != null) {
                    info.out.println("There is already a room there!");
                } else {
                    room = new RoomNode(info.rooms.getUniqueRoomID(), description.toString());
                    info.getCurrentRoom().setUp(room);
                    room.setDown(info.getCurrentRoom());           
                }
                break;

            case "N":
            case "NORTH":
                if (info.getCurrentRoom().getNorth() != null) {
                    info.out.println("There is already a room there!");
                } else {
                    room = new RoomNode(info.rooms.getUniqueRoomID(), description.toString());
                    info.getCurrentRoom().setNorth(room);
                    room.setSouth(info.getCurrentRoom());             
                }
                break;

            case "E":
            case "EAST":
                if (info.getCurrentRoom().getEast() != null) {
                    info.out.println("There is already a room there!");
                } else {
                    room = new RoomNode(info.rooms.getUniqueRoomID(), description.toString());
                    info.getCurrentRoom().setEast(room);
                    room.setWest(info.getCurrentRoom());      
                }
                break;

            case "S":
            case "SOUTH":
                if (info.getCurrentRoom().getSouth() != null) {
                    info.out.println("There is already a room there!");
                } else {
                    room = new RoomNode(info.rooms.getUniqueRoomID(), description.toString());
                    info.getCurrentRoom().setSouth(room);
                    room.setNorth(info.getCurrentRoom());             
                }
                break;

            case "W":
            case "WEST":
                if (info.getCurrentRoom().getWest() != null) {
                    info.out.println("There is already a room there!");
                } else {
                    room = new RoomNode(info.rooms.getUniqueRoomID(), description.toString());
                    info.getCurrentRoom().setWest(room);
                    room.setEast(info.getCurrentRoom());        
                }
                break;
                
            default:
                info.out.print("Direction not found, ");
                error = true;
            }
            
            if (room != null) {
                info.rooms.trackRoom(room);
                info.out.println("Created Room!");
            }
            
        }
        
        if (error) {
            info.out.print("Usage: \"dig <direction> <name>\"\n");
        }
    }

}
