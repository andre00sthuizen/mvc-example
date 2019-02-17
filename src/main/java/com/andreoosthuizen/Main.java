package com.andreoosthuizen;

/**
 * Main entry point for the assignment
 *
 * @author Andre Oosthuizen
 */
public class Main {

    public static void main(String[] args) {
        DrawApplication drawApplication = new DrawApplication();
        Thread thread = new Thread(drawApplication);
        thread.start();
    }

}
