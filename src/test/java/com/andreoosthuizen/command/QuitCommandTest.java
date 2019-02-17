package com.andreoosthuizen.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuitCommandTest {

    @Test
    void should_ReturnFalse_When_InputIsEmptyString() {
        QuitCommand quitCommand = new QuitCommand();
        assertFalse(quitCommand.canExecute(""));
    }

    @Test
    void should_ReturnFalse_When_InputIsNull() {
        QuitCommand quitCommand = new QuitCommand();
        assertFalse(quitCommand.canExecute(null));
    }

    @Test
    void should_ReturnTrue_When_InputMatchesPattern() {
        QuitCommand quitCommand = new QuitCommand();
        assertTrue(quitCommand.canExecute("Q"));
    }

    @Test
    void should_ReturnFalse_When_ExecuteWithInput() {
        QuitCommand quitCommand = new QuitCommand();
        assertFalse(quitCommand.execute("Q", null));
    }

}