package com.andreoosthuizen.command;

import com.andreoosthuizen.controller.Controller;
import com.andreoosthuizen.model.Fill;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Should fill the entire area connected to (x,y) with "colour" c.
 * The behaviour of this is the same as that of the "bucket fill" tool in paint programs.
 * The input string should be in the following format: "B x y c" where x and y are integer values and c is any fill character
 * e.g. B 1 2 o
 *
 * @author Andre Oosthuizen
 */
public class FillCommand implements Command {

    private static final Pattern PATTERN = Pattern.compile("B\\s(\\d+)\\s(\\d+)\\s(.)");
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
            char fillCharacter = Character.valueOf(matcher.group(3).toCharArray()[0]);
            Fill fill = new Fill(width, height, fillCharacter);
            controller.draw(fill);
        }
        return KEEP_RUNNING;
    }

}
