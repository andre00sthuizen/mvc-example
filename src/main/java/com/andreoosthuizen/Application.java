package com.andreoosthuizen;

import com.andreoosthuizen.controller.Controller;
import com.andreoosthuizen.model.Canvas;
import com.andreoosthuizen.view.ConsoleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    /**
     * TODO Dependency injection
     * TODO Handle empty lines and invalid inputs
     */
    public static void main(String[] args) {
        Controller controller = new Controller(new ConsoleView(), new Canvas());
        List<Command> availableCommands = getAvailableCommands(controller);
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.print("enter command: ");
            String input = scanner.nextLine();
            if (input.equals("Q")) {
                run = false;
            } else {
                boolean commandExecuted = false;
                for (Command command: availableCommands) {
                    commandExecuted |= command.execute(input);
                }
                if (!commandExecuted) {
                    printHelpMessage();
                }
                System.out.println();
            }
        }
    }

    private static List<Command> getAvailableCommands(Controller controller) {
        List<Command> availableCommands = new ArrayList<>(4);
        availableCommands.add(new CreateCommand(controller));
        availableCommands.add(new DrawCornerCommand(controller));
        availableCommands.add(new DrawRectangleCommand(controller));
        return availableCommands;
    }

    private static void printHelpMessage() {
        System.out.println("Unsupported input. Valid inputs are:\n" +
                "C w h           Should create a new canvas of width w and height h.\n" +
                "L x1 y1 x2 y2   Should create a new line from (x1,y1) to (x2,y2). Currently only\n" +
                "                horizontal or vertical lines are supported. Horizontal and vertical lines\n" +
                "                will be drawn using the 'x' character.\n" +
                "R x1 y1 x2 y2   Should create a new rectangle, whose upper left corner is (x1,y1) and\n" +
                "                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn\n" +
                "                using the 'x' character.\n" +
                "B x y c         Should fill the entire area connected to (x,y) with \"colour\" c. The\n" +
                "                behaviour of this is the same as that of the \"bucket fill\" tool in paint\n" +
                "                programs.\n" +
                "Q               Should quit the program.\n");
    }

}
