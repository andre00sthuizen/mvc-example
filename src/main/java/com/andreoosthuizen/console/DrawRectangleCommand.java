package com.andreoosthuizen.console;

import com.andreoosthuizen.controller.Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DrawRectangleCommand implements Command {

    private static final Pattern PATTERN = Pattern.compile("R\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)");
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
            controller.drawRectangle(x1, y1, x2, y2);
        }
        return KEEP_RUNNING;
    }

}
