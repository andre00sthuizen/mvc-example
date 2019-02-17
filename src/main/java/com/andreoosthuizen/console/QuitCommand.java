package com.andreoosthuizen.console;

import java.util.regex.Pattern;

/**
 * Terminates the application.
 * We simply return false after completing the execute method
 * The input string should be in the following format: Q
 *
 * @author Andre Oosthuizen
 */
public class QuitCommand implements Command {

    private static final Pattern PATTERN = Pattern.compile("Q");
    private static final boolean STOP_RUNNING = false;

    @Override
    public boolean canExecute(String input) {
        if (input == null) {
            return false;
        }
        return PATTERN.matcher(input).matches();
    }

    @Override
    public boolean execute(String input) {
        return STOP_RUNNING;
    }

}
