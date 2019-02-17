package com.andreoosthuizen.console;

import com.andreoosthuizen.controller.Controller;
import com.andreoosthuizen.model.Canvas;
import com.andreoosthuizen.view.ConsoleView;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleApplication implements Runnable {

    private OutputStream outputStream = System.out;
    private InputStream inputStream = System.in;

    @Override
    public void run() {
        Controller controller = new Controller(new ConsoleView(), new Canvas());
        List<Command> availableCommands = getAvailableCommands(controller);
        Scanner scanner = new Scanner(inputStream);
        PrintStream printStream = new PrintStream(outputStream);
        boolean run = true;
        while (run) {
            printStream.print("enter command: ");
            String input = scanner.nextLine();
            Command command = getCommand(input, availableCommands);
            if (command != null) {
                run = command.execute(input);
            } else {
                printHelpMessage();
            }
            printStream.println();
        }
    }

    private List<Command> getAvailableCommands(Controller controller) {
        List<Command> availableCommands = new ArrayList<>(4);
        availableCommands.add(new CreateCommand(controller));
        availableCommands.add(new DrawLineCommand(controller));
        availableCommands.add(new DrawRectangleCommand(controller));
        availableCommands.add(new QuitCommand());
        return availableCommands;
    }

    private Command getCommand(String input, List<Command> availableCommands) {
        for (Command command: availableCommands) {
            if (command.canExecute(input)) {
                return command;
            }
        }
        return null;
    }

    private void printHelpMessage() {
        PrintStream printStream = new PrintStream(outputStream);
        printStream.println("Invalid input, valid options are:\n" +
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

    OutputStream getOutputStream() {
        return outputStream;
    }

    void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    InputStream getInputStream() {
        return inputStream;
    }

    void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}
