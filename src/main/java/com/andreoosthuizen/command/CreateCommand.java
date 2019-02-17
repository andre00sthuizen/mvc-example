package com.andreoosthuizen.command;

import com.andreoosthuizen.controller.Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Creates a new Canvas according to the dimensions provided.
 * The input string should be in the following format: "C w h" where w and h are integer values are not less than zero.
 * e.g. C 1 2
 *
 * @author Andre Oosthuizen
 */
public class CreateCommand implements Command {

    private static final Pattern PATTERN = Pattern.compile("C\\s(\\d+)\\s(\\d+)");
    private static final boolean KEEP_RUNNING = true;

    @Override
    public boolean canExecute(String input) {
        if (input == null) {
            return false;
        }
        return PATTERN.matcher(input).matches();
    }

    @Override
    public boolean execute(String input, Controller controller) {
        Matcher matcher = PATTERN.matcher(input);
        if (matcher.find()) {
            int width = Integer.valueOf(matcher.group(1));
            int height = Integer.valueOf(matcher.group(2));
            controller.createCanvas(width, height);
        }
        return KEEP_RUNNING;
    }

}
