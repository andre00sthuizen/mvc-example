package com.andreoosthuizen;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DrawApplicationTest {

    @Test
    void should_ReturnSystemOut_When_GetOutputStream() {
        DrawApplication drawApplication = new DrawApplication();
        assertEquals(System.out, drawApplication.getOutputStream());
    }

    @Test
    void should_ReturnSystemIn_When_GetInputStream() {
        DrawApplication drawApplication = new DrawApplication();
        assertEquals(System.in, drawApplication.getInputStream());
    }

    @Test
    void should_ShowPrompt_When_StartedAndQuitCommandIssued() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("Q".getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DrawApplication drawApplication = new DrawApplication();
        drawApplication.setInputStream(byteArrayInputStream);
        drawApplication.setOutputStream(byteArrayOutputStream);
        drawApplication.run();
        assertEquals("enter command: \n", byteArrayOutputStream.toString());
    }

    @Test
    void should_ShowHelpMessage_When_InvalidCommandCommandIssued() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("invalid\nQ".getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DrawApplication drawApplication = new DrawApplication();
        drawApplication.setInputStream(byteArrayInputStream);
        drawApplication.setOutputStream(byteArrayOutputStream);
        drawApplication.run();
        assertTrue(byteArrayOutputStream.toString().contains("Invalid input"));
    }

}