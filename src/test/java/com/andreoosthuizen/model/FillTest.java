package com.andreoosthuizen.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

public class FillTest {

    @Test
    void should_IgnoreBucketFill_When_XBelowBound() {
        Raster raster = new Raster(1, 1);
        Fill fill = new Fill(-1, 0, 'o');
        fill.paint(raster);
        assertEquals(' ', raster.getPixel(0, 0));
    }

    @Test
    void should_IgnoreBucketFill_When_XAboveBound() {
        Raster raster = new Raster(1, 1);
        Fill fill = new Fill(3, 0, 'o');
        fill.paint(raster);
        assertEquals(' ', raster.getPixel(0, 0));
    }

    @Test
    void should_IgnoreBucketFill_When_YBelowBound() {
        Raster raster = new Raster(1, 1);
        Fill fill = new Fill(0, -1, 'o');
        fill.paint(raster);
        assertEquals(' ', raster.getPixel(0, 0));
    }

    @Test
    void should_IgnoreBucketFill_When_YAboveBound() {
        Raster raster = new Raster(1, 1);
        Fill fill = new Fill(0, 3, 'o');
        fill.paint(raster);
        assertEquals(' ', raster.getPixel(0, 0));
    }

    @Test
    void should_Fill1By1RasterWithChar_When_BucketFillPaint() {
        Raster raster = new Raster(1, 1);
        Fill fill = new Fill(0, 0, 'o');
        fill.paint(raster);
        assertEquals('o', raster.getPixel(0, 0));
    }

    @Test
    void should_IgnoreFill1By1RasterWithChar_When_BucketFillPaintOnSameChar() {
        Raster raster = new Raster(1, 1);
        Fill fill = new Fill(0, 0, 'o');
        fill.paint(raster);
        assertEquals('o', raster.getPixel(0, 0));
    }

    @Test
    void should_ReturnTrue_When_TwoIdenticalFillsCompared() {
        Fill fill1 = new Fill(1, 2, 'x');
        Fill fill2 = new Fill(1, 2, 'x');
        assertTrue(fill1.equals(fill2));
    }

    @Test
    void should_ReturnSameHashcode_When_TwoIdenticalFillsCompared() {
        Fill fill1 = new Fill(1, 2, 'x');
        Fill fill2 = new Fill(1, 2, 'x');
        assertTrue(fill1.hashCode() == fill2.hashCode());
    }

}
