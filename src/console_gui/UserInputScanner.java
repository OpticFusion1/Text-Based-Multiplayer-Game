package console_gui;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commands.RunnableCommand;
import commands.CommandNotFoundCommand;
import commands.DigCommand;
import commands.DownCommand;
import commands.EastCommand;
import commands.ExamineCommand;
import commands.HelpCommand;
import commands.InspectCommand;
import commands.ItemCommand;
import commands.ConnectCommand;
import commands.LookCommand;
import commands.NorthCommand;
import commands.QuitCommand;
import commands.Command;
import commands.SouthCommand;
import commands.TeleportCommand;
import commands.UnLinkCommand;
import commands.UpCommand;
import commands.SeparateCommand;
import commands.SetCommand;
import commands.WestCommand;

/**
 * This class deals with handling user input. Which comes in the form of getting commands or just getting entire lines
 * of text from the user.
 * 
 * @author Zachary Chandler
 */
public class UserInputScanner {
    
    /**
     * The commands this scanner recognizes. 
     */
    public static final Set<Command> COMMANDS = Collections.unmodifiableSet(new TreeSet<Command>(Arrays.asList(new Command[] {
            new CommandNotFoundCommand(),
            new QuitCommand(),
            new DownCommand(),
            new UpCommand(),
            new NorthCommand(),
            new EastCommand(),
            new SouthCommand(),
            new WestCommand(),
            new LookCommand(),
            new DigCommand(),
            new SeparateCommand(),
            new UnLinkCommand(),
            new ExamineCommand(),
            new TeleportCommand(),
            new ConnectCommand(),
            new InspectCommand(),
            new SetCommand(),
            new ItemCommand(),
            new HelpCommand()
    })));
    
    /** A map of aliases to commands for fast lookup time. */
    public static final Map<String, Command> COMMAND_MAP = Collections.unmodifiableMap(new TreeMap<String, Command>() {
        
        /** Generated SVUID */
        private static final long serialVersionUID = 1106589250798615744L;
        
        public TreeMap<String, Command> getMap() {
            for (Command c : COMMANDS) {
                String[] aliases = c.getAliases();
                for (int i = 0; i < aliases.length; i++) {
                    this.put(aliases[i].toUpperCase(), c);
                }
            }
            return this;
        }
    }.getMap());
    
    /** Text that will not be broken up by. */
    private static final Pattern TOKEN_TEXT = Pattern.compile("\"[^\"]*\"|<[^>^<]*>|\\[[^\\[^\\]]*\\]|[^\\s^\"^<^>^\\[^\\]]+");
    
    /** Commands that have been queued up by the user. */
    private LinkedList<RunnableCommand> commands;
    
    /** The scanner we will use to get input. */
    private Scanner input;
    
    /**
     * Instantiate the class on the given InputStream.
     * 
     * @param in the input stream from the user.
     */
    public UserInputScanner(InputStream in) {
        this.commands = new LinkedList<RunnableCommand>();
        this.input = new Scanner(in);
    }
    
    /**
     * Just get the next line from the user.
     * @return the next line of input from the user.
     */
    public String nextLine() {
        return input.nextLine();
    }
    
    /**
     * If the user has any Queued commands, wise input prompters can use this information to avoid giving prompts to 
     * commands that have already been scanned.
     * @return if there are any commands waiting to be run.
     */
    public boolean hasQueuedCommand() {
        return !commands.isEmpty();
    }
    
    /**
     * Inserts the given command to be run next.
     * Subsequent calls will always run next.
     * NextCommand cannot be null.
     * 
     * @param nextCommand the command to run.
     * @throws NullPointerException if nextCommand is null.
     */
    public void insertNextCommand(RunnableCommand nextCommand) {
        if (nextCommand == null) {
            throw new NullPointerException("Cannot add null as next command in UserInputScanner!");
        }
        commands.addFirst(nextCommand);
    }
    
    /**
     * @return the next command from the user.
     */
    public RunnableCommand getNextCommand() {
        RunnableCommand result;
        
        if (commands.isEmpty()) {
            String[] semiSplit = input.nextLine().split("(\\s*;+\\s*)+");
            
            for (int i = 0; i < semiSplit.length; i++) {
                RunnableCommand c = translateCommmand(semiSplit[i]);
                
                if (c != null) {
                    commands.add(c);
                }
            }
        }
        
        result = commands.poll();
        
        return result;
    }
    
    /**
     * Translates a given string to a command.
     * @param s the string to parse.
     * @return the resulting command.
     */
    public RunnableCommand translateCommmand(String s) {
        RunnableCommand result;
        
        String[] args = getCommandArguments(s);
        
        if (args.length > 0) {
            Command runner = COMMAND_MAP.get(args[0].toUpperCase());
            
            if (runner == null) {
                runner = COMMAND_MAP.get(CommandNotFoundCommand.LOOK_UP_STRING);
            }
            
            result = new RunnableCommand(args, runner);
        } else {
            result = new RunnableCommand(args, COMMAND_MAP.get(CommandNotFoundCommand.LOOK_UP_STRING));
        }
        
        return result;
    }
    
    /**
     * Get the arguments of the command.
     * @param s the raw command.
     * @return the arguments.
     */
    public static String[] getCommandArguments(String s) {
        Matcher m = TOKEN_TEXT.matcher(s);
        LinkedList<String> tokens = new LinkedList<>();
        
        while(m.find()) {
            String match = m.group();
            match = cleanPreserveCharacters(match);
            tokens.add(match);
        }
        
        return tokens.toArray(new String[0]);
    }
    
    /**
     * Cleans of extra characters that were used to preserve the characters inside them.
     * 
     * @param match that match to clean off
     * @return a new string without the extra characters at either end.
     */
    public static String cleanPreserveCharacters(String match) {
        if ((match.startsWith("\"") && match.endsWith("\"") && match.length() > 1) ||
            (match.startsWith("<") && match.endsWith(">")) ||
            (match.startsWith("[") && match.endsWith("]")) ) {
            match = match.substring(1, match.length() - 1);
        }
        
        return match;
    }
}
