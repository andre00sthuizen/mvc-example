package com.andreoosthuizen;

import com.andreoosthuizen.controller.Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateCommand implements Command {

    private static final Pattern PATTERN = Pattern.compile("C\\s(\\d+)\\s(\\d+)");
    private Controller controller;

    public CreateCommand(Controller controller) {
        this.controller = controller;
    }

    @Override
    public boolean execute(String input) {
        Matcher matcher = PATTERN.matcher(input);
        if (matcher.find()) {
            int width = Integer.valueOf(matcher.group(1));
            int height = Integer.valueOf(matcher.group(2));
            controller.createCanvas(width, height);
        }
        return matcher.matches();
    }

}
