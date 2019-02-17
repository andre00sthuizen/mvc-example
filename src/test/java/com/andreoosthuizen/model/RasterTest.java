package com.andreoosthuizen.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RasterTest {

    @Test
    void should_ThrowIllegalArugmentExeption_When_CreateWithNegativeX() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Raster(-1, 1);
        });
    }

    @Test
    void should_ThrowIllegalArugmentExeption_When_CreateWithZeroX() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Raster(0, 1);
        });
    }

    @Test
    void should_ThrowIllegalArugmentExeption_When_CreateWithNegativeY() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Raster(1, -1);
        });
    }

    @Test
    void should_ThrowIllegalArugmentExeption_When_CreateWithZeroY() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Raster(1, 0);
        });
    }

    @Test
    void should_IgnoreSetPixel_When_SetPixelXUnderRasterBounds() {
        Raster raster = new Raster(1, 1);
        raster.setPixel(-1, 0, 'X');
        assertEquals(' ', raster.getPixel(0, 0));
    }

    @Test
    void should_IgnoreSetPixel_When_SetPixelXOverRasterBounds() {
        Raster raster = new Raster(1, 1);
        raster.setPixel(3, 0, 'X');
        assertEquals(' ', raster.getPixel(0, 0));
    }

    @Test
    void should_IgnoreSetPixel_When_SetPixelYUnderRasterBounds() {
        Raster raster = new Raster(1, 1);
        raster.setPixel(0, -1, 'X');
        assertEquals(' ', raster.getPixel(0, 0));
    }

    @Test
    void should_IgnoreSetPixel_When_SetPixelYOverRasterBounds() {
        Raster raster = new Raster(1, 1);
        raster.setPixel(0, 3, 'X');
        assertEquals(' ', raster.getPixel(0, 0));
    }

    @Test
    void should_ReturnAssingedChar_When_SetPixelAt1By1Raster() {
        Raster raster = new Raster(1, 1);
        raster.setPixel(0, 0, 'X');
        assertEquals('X', raster.getPixel(0, 0));
    }

    @Test
    void should_ReturnAssingedChar_When_SetPixelAt10By10() {
        Raster raster = new Raster(10, 10);
        raster.setPixel(0, 9, 'X');
        assertEquals('X', raster.getPixel(0, 9));
    }

    @Test
    void should_ReturnSpaceChar_When_SetPixelAt1By1Raster() {
        Raster raster = new Raster(1, 1);
        assertEquals(' ', raster.getPixel(0, 0));
    }

}
