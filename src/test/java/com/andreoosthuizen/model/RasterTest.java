package com.andreoosthuizen.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RasterTest {

    @Test
    void should_ThrowIllegalArugmentExeption_When_SetPixelWithNegativeX() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Raster(-1, 1);
        });
    }

    @Test
    void should_ThrowIllegalArugmentExeption_When_SetPixelWithZeroX() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Raster(0, 1);
        });
    }

    @Test
    void should_ThrowIllegalArugmentExeption_When_SetPixelWithNegativeY() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Raster(1, -1);
        });
    }

    @Test
    void should_ThrowIllegalArugmentExeption_When_SetPixelWithZeroY() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Raster(1, 0);
        });
    }

    @Test
    void should_ThrowIndexOutOfBoundsException_When_SetXLessThanZero() {
        Raster raster = new Raster(1, 1);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            raster.setPixel(-1, 0, 'X');
        });
    }

    @Test
    void should_ThrowIndexOutOfBoundsException_When_SetYLessThanZero() {
        Raster raster = new Raster(1, 1);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            raster.setPixel(0, -1, 'X');
        });
    }

    @Test
    void should_ThrowIndexOutOfBoundsException_When_SetXGreaterThanWidth() {
        Raster raster = new Raster(1, 1);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            raster.setPixel(1, 0, 'X');
        });
    }

    @Test
    void should_ThrowIndexOutOfBoundsException_When_SetYGreaterThanHeight() {
        Raster raster = new Raster(1, 1);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            raster.setPixel(0, 1, 'X');
        });
    }

    @Test
    void should_ReturnAssingedChar_When_SetPixelAt1By1Raster() {
        Raster raster = new Raster(1, 1);
        raster.setPixel(0, 0, 'X');
        assertEquals('X', raster.getPixels()[0][0]);
    }

    @Test
    void should_ReturnAssingedChar_When_SetPixelAt10By10() {
        Raster raster = new Raster(10, 10);
        raster.setPixel(0, 9, 'X');
        assertEquals('X', raster.getPixels()[0][9]);
    }

    @Test
    void should_ReturnSpaceChar_When_SetPixelAt1By1Raster() {
        Raster raster = new Raster(1, 1);
        assertEquals(' ', raster.getPixels()[0][0]);
    }

}
