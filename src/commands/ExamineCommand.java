package commands;

import console_gui.UserInformation;

public class ExamineCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"EXA", "EXAMINE"};
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        if (args.length < 1) {
            throw new ArrayIndexOutOfBoundsException("Args should have atleast 1 argument.");
        } else if (args.length == 1) {
            info.out.println("Usage, \"examine room");
        } else {
            switch(args[1].toUpperCase()) {
            case "ROOM":
                info.out.print("RoomID: ");
                info.out.print(info.getCurrentRoom().getRoomID());
                info.out.print('\n');
                break;
                
            default:
                info.out.println("Item examination not implemented.");
            }
            
        }
    }

}
