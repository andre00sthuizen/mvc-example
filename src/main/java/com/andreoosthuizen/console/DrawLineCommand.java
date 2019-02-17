package com.andreoosthuizen.console;

import com.andreoosthuizen.controller.Controller;
import com.andreoosthuizen.model.Line;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Draws either a vertical or horizontal line from (x1,y1) to (x2,y2)
 *
 * @author Andre Oosthuizen
 */
public class DrawLineCommand implements Command {

    private static final Pattern PATTERN = Pattern.compile("L\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)");
    private static final char DRAW_CHARACTER = 'x';
    private static final boolean KEEP_RUNNING = true;
    private Controller controller;

    public DrawLineCommand(Controller controller) {
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
            Line line = new Line(x1, y1, x2, y2, DRAW_CHARACTER);
            controller.draw(line);
        }
        return KEEP_RUNNING;
    }

}
