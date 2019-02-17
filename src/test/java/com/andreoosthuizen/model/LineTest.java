package com.andreoosthuizen.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LineTest {

    @Test
    void should_ThrowIllegalArgumentException_When_NotCreatedHorizontalOrVertical() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Line(1, 2, 3, 4, 'x');
        });
    }

    @Test
    void should_NotThrowIllegalArgumentException_When_CreatedHorizontal() {
        new Line(1, 2, 1, 3, 'x');
    }

    @Test
    void should_NotThrowIllegalArgumentException_When_CreatedVertical() {
        new Line(1, 2, 3, 2, 'x');
    }

    @Test
    void should_ReturnTrue_When_TwoIdenticalLinesCompared() {
        Line line1 = new Line(1, 2, 3, 2, 'x');
        Line line2 = new Line(1, 2, 3, 2, 'x');
        assertTrue(line1.equals(line1));
        assertTrue(line1.equals(line2));
    }

    @Test
    void should_ReturnFalse_When_TwoFillsComparedOnsIsNull() {
        Line line1 = new Line(1, 2, 3, 2, 'x');
        Line line2 = new Line(1, 2, 1, 3, 'y');
        assertFalse(line1.equals(null));
        assertFalse(line2.equals(null));
        assertFalse(line1.equals(line2));
        assertFalse(line1.equals(new String()));
    }

    @Test
    void should_ReturnSameHashcode_When_TwoIdenticalLinesCompared() {
        Line line1 = new Line(1, 2, 3, 2, 'x');
        Line line2 = new Line(1, 2, 3, 2, 'x');
        assertTrue(line1.hashCode() == line2.hashCode());
    }

    @Test
    void should_CreateRasterWithPixelTopLeft_When_PaintWith0000() {
        Raster raster = new Raster(2, 2);
        Line line = new Line(0, 0, 0, 0, 'x');
        line.paint(raster);
        assertEquals('x', raster.getPixel(0, 0));
        assertEquals(' ', raster.getPixel(1, 0));
        assertEquals(' ', raster.getPixel(0, 1));
        assertEquals(' ', raster.getPixel(1, 1));
    }

    @Test
    void should_CreateRasterWithPixelTopRight_When_PaintWith1010() {
        Raster raster = new Raster(2, 2);
        Line line = new Line(1, 0, 1, 0, 'x');
        line.paint(raster);
        assertEquals(' ', raster.getPixel(0, 0));
        assertEquals('x', raster.getPixel(1, 0));
        assertEquals(' ', raster.getPixel(0, 1));
        assertEquals(' ', raster.getPixel(1, 1));
    }

    @Test
    void should_CreateRasterWithPixelBottomLeft_When_PaintWith0101() {
        Raster raster = new Raster(2, 2);
        Line line = new Line(0, 1, 0, 1, 'x');
        line.paint(raster);
        assertEquals(' ', raster.getPixel(0, 0));
        assertEquals(' ', raster.getPixel(1, 0));
        assertEquals('x', raster.getPixel(0, 1));
        assertEquals(' ', raster.getPixel(1, 1));
    }

    @Test
    void should_CreateRasterWithPixelBottomRight_When_PaintWith1111() {
        Raster raster = new Raster(2, 2);
        Line line = new Line(1, 1, 1, 1, 'x');
        line.paint(raster);
        assertEquals(' ', raster.getPixel(0, 0));
        assertEquals(' ', raster.getPixel(1, 0));
        assertEquals(' ', raster.getPixel(0, 1));
        assertEquals('x', raster.getPixel(1, 1));
    }

    @Test
    void should_CreateRasterWithHorizontalPixels_When_PaintWith2131() {
        Raster raster = new Raster(4, 3);
        Line line = new Line(2, 1, 3, 1, 'x');
        line.paint(raster);
        for (int i=0; i<raster.getWidth(); i++) {
            for (int j=0; j<raster.getHeight(); j++) {
                if (j == 1 && (i == 2 || i == 3)) {
                    assertEquals('x', raster.getPixel(i, j));
                } else {
                    assertEquals(' ', raster.getPixel(i, j));
                }
            }
        }
    }

    @Test
    void should_CreateRasterWithVerticalPixels_When_PaintWith1213() {
        Raster raster = new Raster(3, 4);
        Line line = new Line(1, 2, 1, 3, 'x');
        line.paint(raster);
        for (int i=0; i<raster.getWidth(); i++) {
            for (int j=0; j<raster.getHeight(); j++) {
                if (i == 1 && (j == 2 || j == 3)) {
                    assertEquals('x', raster.getPixel(i, j));
                } else {
                    assertEquals(' ', raster.getPixel(i, j));
                }
            }
        }
    }

    @Test
    void should_IgnoreXPixelsThatContinueBeyondRaster_When_Paint() {
        Raster raster = new Raster(2, 2);
        Line line = new Line(0, 0, 100, 0, 'x');
        line.paint(raster);
        assertEquals('x', raster.getPixel(0, 0));
        assertEquals('x', raster.getPixel(1, 0));
        assertEquals(' ', raster.getPixel(0, 1));
        assertEquals(' ', raster.getPixel(1, 1));
    }

    @Test
    void should_IgnoreXPixelsThatStartBeforeRaster_When_Paint() {
        Raster raster = new Raster(2, 2);
        Line line = new Line(-100, 0, 1, 0, 'x');
        line.paint(raster);
        assertEquals('x', raster.getPixel(0, 0));
        assertEquals('x', raster.getPixel(1, 0));
        assertEquals(' ', raster.getPixel(0, 1));
        assertEquals(' ', raster.getPixel(1, 1));
    }

    @Test
    void should_IgnoreXPixelsThatAreHorizontallyOffCanvas_When_Paint() {
        Raster raster = new Raster(2, 2);
        Line line = new Line(-100, 0, -1, 0, 'x');
        line.paint(raster);
        for (int i=0; i<raster.getWidth(); i++) {
            for (int j=0; j<raster.getHeight(); j++) {
                assertEquals(' ', raster.getPixel(i, j));
            }
        }
    }

    @Test
    void should_IgnoreXPixelsThatAreVerticallyOffCanvas_When_Paint() {
        Raster raster = new Raster(2, 2);
        Line line = new Line(0, -1, 0, -1, 'x');
        line.paint(raster);
        for (int i=0; i<raster.getWidth(); i++) {
            for (int j=0; j<raster.getHeight(); j++) {
                assertEquals(' ', raster.getPixel(i, j));
            }
        }
    }

}
