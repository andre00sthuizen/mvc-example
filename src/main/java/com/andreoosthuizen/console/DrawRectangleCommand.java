package com.andreoosthuizen.console;

import com.andreoosthuizen.controller.Controller;
import com.andreoosthuizen.model.Line;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Draws a rectangular shape where the upper left corner is (x1,y1)
 * and the lower right corner is (x2,y2)
 *
 * @author Andre Oosthuizen
 */
public class DrawRectangleCommand implements Command {

    private static final Pattern PATTERN = Pattern.compile("R\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)");
    private static final char DRAW_CHARACTER = 'x';
    private static final boolean KEEP_RUNNING = true;
    private Controller controller;

    public DrawRectangleCommand(Controller controller) {
        this.controller = controller;
    }

    @Override
    public boolean canExecute(String input) {
        if (input == null) {
            return false;
        }
        return PATTERN.matcher(input).matches();
    }

    @Override
    public boolean execute(String input) {
        Matcher matcher = PATTERN.matcher(input);
        if (matcher.find()) {
            int x1 = Integer.valueOf(matcher.group(1));
            int y1 = Integer.valueOf(matcher.group(2));
            int x2 = Integer.valueOf(matcher.group(3));
            int y2 = Integer.valueOf(matcher.group(4));
            Line top = new Line(x1, y1, x2, y1, DRAW_CHARACTER);
            controller.paintDrawable(top);
            Line bottom = new Line(x1, y2, x2, y2, DRAW_CHARACTER);
            controller.paintDrawable(bottom);
            Line left = new Line(x1, y1, x1, y2, DRAW_CHARACTER);
            controller.paintDrawable(left);
            Line right = new Line(x2, y1, x2, y2, DRAW_CHARACTER);
            controller.paintDrawable(right);
        }
        return KEEP_RUNNING;
    }

}
