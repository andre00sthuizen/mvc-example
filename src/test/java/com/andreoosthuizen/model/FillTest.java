package com.andreoosthuizen.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(fill1.equals(fill1));
        assertTrue(fill1.equals(fill2));
    }

    @Test
    void should_ReturnFalse_When_TwoFillsComparedOnsIsNull() {
        Fill fill1 = new Fill(1, 2, 'x');
        Fill fill2 = new Fill(2, 3, 'y');
        assertFalse(fill1.equals(null));
        assertFalse(fill2.equals(null));
        assertFalse(fill1.equals(fill2));
        assertFalse(fill1.equals(new String()));
    }

    @Test
    void should_ReturnSameHashcode_When_TwoIdenticalFillsCompared() {
        Fill fill1 = new Fill(1, 2, 'x');
        Fill fill2 = new Fill(1, 2, 'x');
        assertTrue(fill1.hashCode() == fill2.hashCode());
    }

    @Test
    void should_Return_When_TargetCharacterIsEqualToReplacementCharacter() {
        Raster raster = new Raster(1, 1);
        raster.setPixel(0, 0, 'o');
        Fill fill = new Fill(0, 0, 'o');
        fill.paint(raster);
        assertEquals('o', raster.getPixel(0, 0));
    }

    @Test
    void should__Fill1By1RasterWithChar_When_PixelCharacterIsNotEqualToBlankCharacter() {
        Raster raster = new Raster(1, 1);
        raster.setPixel(0, 0, 'o');
        Fill fill = new Fill(0, 0, 'x');
        fill.paint(raster);
        assertEquals('x', raster.getPixel(0, 0));
    }

}
