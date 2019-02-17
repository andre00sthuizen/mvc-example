package com.andreoosthuizen.view;

import com.andreoosthuizen.model.RasterDefault;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ViewDefaultTest {

    @Test
    void should_ThrowIllegalArgumentException_When_RenderWithNullRaster() {
        ViewDefault viewDefault = new ViewDefault();
        assertThrows(IllegalArgumentException.class, () -> {
            viewDefault.render(null);
        }, "Expected IllegalArgumentException for null raster input");
    }

    @Test
    void should_ReturnSystemOut_When_GetOutputStream() {
        ViewDefault viewDefault = new ViewDefault();
        assertEquals(System.out, viewDefault.getOutputStream());
    }

    @Test
    void should_ReturnGivenOutputStream_When_SetOutputStream() {
        ViewDefault viewDefault = new ViewDefault();
        OutputStream outputStream = new ByteArrayOutputStream();
        viewDefault.setOutputStream(outputStream);
        assertEquals(outputStream, viewDefault.getOutputStream());
    }

    @Test
    void should_RenderCharacterAsString_When_Given1By1Raster() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ViewDefault viewDefault = new ViewDefault();
        viewDefault.setOutputStream(outputStream);
        RasterDefault raster = new RasterDefault(1, 1);
        raster.setPixel(0, 0, 'X');
        viewDefault.render(raster);
        assertEquals("X\n", outputStream.toString());
    }

    @Test
    void should_RenderCorrectOrientation_When_Given3By2Raster() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ViewDefault viewDefault = new ViewDefault();
        viewDefault.setOutputStream(outputStream);
        RasterDefault raster = new RasterDefault(3, 2);
        raster.setPixel(0, 0, 'X');
        raster.setPixel(2, 1, 'X');
        viewDefault.render(raster);
        assertEquals("X  \n  X\n", outputStream.toString());
    }

}