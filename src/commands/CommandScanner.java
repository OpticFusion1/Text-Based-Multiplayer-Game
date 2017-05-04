package commands;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

import console_gui.RunnableCommand;

public class CommandScanner {
    
    private static final RunnableCommand[] COMMANDS = {
            new CommandNotFoundCommand(),
            new QuitCommand(),
            new DownCommand(),
            new UpCommand(),
            new NorthCommand(),
            new EastCommand(),
            new SouthCommand(),
            new WestCommand(),
            new LookCommand()
    };
    
    private Queue<Command> commands;
    private Map<String, RunnableCommand> commandMap;
    private Scanner input;
    private PrintStream out;


    public CommandScanner(InputStream in, PrintStream out) {
        this.commands = new LinkedList<Command>();
        this.input = new Scanner(in);
        this.out = out;
        
        this.commandMap = new TreeMap<>();
        
        for (RunnableCommand c : COMMANDS) {
            String[] aliases = c.getAliases();
            for (int i = 0; i < aliases.length; i++) {
                commandMap.put(aliases[i].toUpperCase(), c);
            }
        }
    }
    
    public Command getNextCommand() {
        Command result;
        
        
        if (commands.isEmpty()) {
            out.print('>');
            String[] args = input.nextLine().split("\\s+");
            
            if (args.length > 0) {
                RunnableCommand runner = commandMap.get(args[0].toUpperCase());
                
                if (runner == null) {
                    runner = commandMap.get(CommandNotFoundCommand.LOOK_UP_STRING);
                }
                
                result = new Command(args, runner);
            } else {
                result = null;                
            }
        } else {
            result = commands.poll();
        }
        
        
        return result;
    }
}
