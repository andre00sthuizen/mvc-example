package com.andreoosthuizen;

import com.andreoosthuizen.command.Command;
import com.andreoosthuizen.command.CommandFactory;
import com.andreoosthuizen.controller.Controller;
import com.andreoosthuizen.controller.DefaultController;
import com.andreoosthuizen.model.Canvas;
import com.andreoosthuizen.view.ConsoleView;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Run with {@link Main}
 */
public class DrawApplication implements Runnable {

    private OutputStream outputStream = System.out;
    private InputStream inputStream = System.in;

    @Override
    public void run() {
        Controller controller = new DefaultController(new ConsoleView(), new Canvas());
        Scanner scanner = new Scanner(inputStream);
        PrintStream printStream = new PrintStream(outputStream);
        boolean run = true;
        while (run) {
            printStream.print("enter command: ");
            String input = scanner.nextLine();
            Command command = CommandFactory.getInstance().getCommand(input);
            if (command != null) {
                command.init(controller);
                run = command.execute(input);
            } else {
                printHelpMessage();
            }
            printStream.println();
        }
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
