package com.andreoosthuizen.command;

import com.andreoosthuizen.controller.Controller;
import com.andreoosthuizen.model.Line;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Should create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2).
 * Horizontal and vertical lines will be drawn using the 'x' character.
 * The input string should be in the following format: R x1 y1 x2 y2 where x and y are integer values
 * e.g. R 1 2 3 4
 *
 * @author Andre Oosthuizen
 */
public class DrawRectangleCommand implements Command {

    private static final Pattern PATTERN = Pattern.compile("R\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)");
    private static final char DRAW_CHARACTER = 'x';
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
            int x1 = Integer.valueOf(matcher.group(1));
            int y1 = Integer.valueOf(matcher.group(2));
            int x2 = Integer.valueOf(matcher.group(3));
            int y2 = Integer.valueOf(matcher.group(4));
            Line top = new Line(x1, y1, x2, y1, DRAW_CHARACTER);
            Line bottom = new Line(x1, y2, x2, y2, DRAW_CHARACTER);
            Line left = new Line(x1, y1, x1, y2, DRAW_CHARACTER);
            Line right = new Line(x2, y1, x2, y2, DRAW_CHARACTER);
            controller.draw(top, bottom, left, right);
        }
        return KEEP_RUNNING;
    }

}
