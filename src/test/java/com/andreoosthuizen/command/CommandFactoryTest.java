package com.andreoosthuizen.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CommandFactoryTest {

    @Test
    void should_ReturnTheQuitCommand_When_InputIsValid() {
        assertNotNull(CommandFactory.getInstance().getCommand("Q"));
    }

    @Test
    void should_ReturnNull_When_InputIsInvalid() {
        assertNull(CommandFactory.getInstance().getCommand("invalid"));
    }

}