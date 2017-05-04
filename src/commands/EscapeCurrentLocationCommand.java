package commands;

import console_gui.CurrentInformation;

public class EscapeCurrentLocationCommand extends RunnableCommand {

    @Override
    public String[] getAliases() {
        return new String[] {"ESCAPECURRENTLOCATION"};
    }

    @Override
    public void runCommand(CurrentInformation info, String[] args) {
        info.setCurrentRoom(info.rooms.getStartingRoom());
        info.out.println(info.getCurrentRoom().getDescription());
    }

}
