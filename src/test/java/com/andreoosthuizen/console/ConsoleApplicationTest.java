package com.andreoosthuizen.console;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsoleApplicationTest {

    @Test
    void should_ReturnSystemOut_When_GetOutputStream() {
        ConsoleApplication consoleApplication = new ConsoleApplication();
        assertEquals(System.out, consoleApplication.getOutputStream());
    }

    @Test
    void should_ReturnSystemIn_When_GetInputStream() {
        ConsoleApplication consoleApplication = new ConsoleApplication();
        assertEquals(System.in, consoleApplication.getInputStream());
    }

    @Test
    void should_ShowPrompt_When_StartedAndQuitCommandIssued() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("Q".getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ConsoleApplication consoleApplication = new ConsoleApplication();
        consoleApplication.setInputStream(byteArrayInputStream);
        consoleApplication.setOutputStream(byteArrayOutputStream);
        consoleApplication.run();
        assertEquals("enter command: \n", byteArrayOutputStream.toString());
    }

    @Test
    void should_ShowHelpMessage_When_InvalidCommandCommandIssued() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("invalid\nQ".getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ConsoleApplication consoleApplication = new ConsoleApplication();
        consoleApplication.setInputStream(byteArrayInputStream);
        consoleApplication.setOutputStream(byteArrayOutputStream);
        consoleApplication.run();
        assertTrue(byteArrayOutputStream.toString().contains("Invalid input"));
    }

}