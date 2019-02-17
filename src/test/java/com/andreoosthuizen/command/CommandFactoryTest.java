package com.andreoosthuizen.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommandFactoryTest {

    @Test
    void getCommand() {
        assertNotNull(CommandFactory.getInstance().getCommand("Q"));
    }

}