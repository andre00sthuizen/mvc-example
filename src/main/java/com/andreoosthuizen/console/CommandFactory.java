package com.andreoosthuizen.console;

import java.util.Iterator;
import java.util.ServiceLoader;

public class CommandFactory {

    private static ServiceLoader<Command> commands = ServiceLoader.load(Command.class);
    private static CommandFactory INSTANCE;

    public static CommandFactory getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CommandFactory();
        }
        return INSTANCE;
    }

    public Command getCommand(String input) {
        Iterator<Command> iterable = commands.iterator();
        while (iterable.hasNext()) {
            Command command = iterable.next();
            if (command.canExecute(input)) {
                return command;
            }
        }
        return null;
    }

}
