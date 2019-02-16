package com.andreoosthuizen.view;

import com.andreoosthuizen.model.Raster;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConsoleViewTest {

    @Test
    void should_ThrowIllegalArgumentException_When_RenderWithNullRaster() {
        ConsoleView consoleView = new ConsoleView();
        assertThrows(IllegalArgumentException.class, () -> {
            consoleView.render(null);
        }, "Expected IllegalArgumentException for null raster input");
    }

    @Test
    void should_ReturnSystemOut_When_GetOutputStream() {
        ConsoleView consoleView = new ConsoleView();
        assertEquals(System.out, consoleView.getOutputStream());
    }

    @Test
    void should_ReturnGivenOutputStream_When_SetOutputStream() {
        ConsoleView consoleView = new ConsoleView();
        OutputStream outputStream = new ByteArrayOutputStream();
        consoleView.setOutputStream(outputStream);
        assertEquals(outputStream, consoleView.getOutputStream());
    }

    @Test
    void should_RenderCharacterAsString_When_Given1By1Raster() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ConsoleView consoleView = new ConsoleView();
        consoleView.setOutputStream(outputStream);
        Raster raster = new Raster(1, 1);
        raster.setPixel(0, 0, 'X');
        consoleView.render(raster);
        assertEquals("X\n", outputStream.toString());
    }

    @Test
    void should_RenderCorrectOrientation_When_Given3By2Raster() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ConsoleView consoleView = new ConsoleView();
        consoleView.setOutputStream(outputStream);
        Raster raster = new Raster(3, 2);
        raster.setPixel(0, 0, 'X');
        raster.setPixel(2, 1, 'X');
        consoleView.render(raster);
        assertEquals("X  \n  X\n", outputStream.toString());
    }

}