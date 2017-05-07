package commands;

import console_gui.UserInformation;

public class ExamineCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"EXA", "EXAMINE"};
    }

    @Override
    public void runCommand(UserInformation info, String[] args) {
        if (args.length == 1) {
            info.out.printf("RoomID: %d\n", info.getCurrentRoom().getRoomID());
            info.out.printf("Room Name: %s\n", info.getCurrentRoom().getName());
            info.out.printf("Room Description: %s\n", info.getCurrentRoom().getDescription());
        } else {   
            info.out.println("Item examination not implemented.");
        }
    }

}
