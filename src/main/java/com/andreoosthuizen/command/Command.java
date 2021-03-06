package com.andreoosthuizen.command;

import com.andreoosthuizen.controller.Controller;

public interface Command {

    /**
     * Check if input String can be handled by this command
     * @param input
     * @return true if the command is capable of executing this input, false if it should not execute
     */
    boolean canExecute(String input);

    /**
     * Execute this command for the given input String
     * @param input and controller
     * @return true if the application should continue to run, or false to terminate
     */
    boolean execute(String input, Controller controller);

}
