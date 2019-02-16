package com.andreoosthuizen;

import com.andreoosthuizen.controller.Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DrawCornerCommand implements Command {

    private static final Pattern PATTERN = Pattern.compile("L\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)");
    private Controller controller;

    public DrawCornerCommand(Controller controller) {
        this.controller = controller;
    }

    @Override
    public boolean execute(String input) {
        Matcher matcher = PATTERN.matcher(input);
        if (matcher.find()) {
            int x1 = Integer.valueOf(matcher.group(1));
            int y1 = Integer.valueOf(matcher.group(2));
            int x2 = Integer.valueOf(matcher.group(1));
            int y2 = Integer.valueOf(matcher.group(2));
            controller.drawCorner(x1, y1, x2, y2);
        }
        return matcher.matches();
    }

}
