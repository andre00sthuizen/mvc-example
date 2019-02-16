package com.andreoosthuizen;

import com.andreoosthuizen.console.ConsoleApplication;

/**
 * Main entry point for the assignment
 *
 * @author Andre Oosthuizen
 */
public class Main {

    public static void main(String[] args) {
        ConsoleApplication consoleApplication = new ConsoleApplication();
        Thread thread = new Thread(consoleApplication);
        thread.start();
    }

}
