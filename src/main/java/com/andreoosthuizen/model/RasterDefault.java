package com.andreoosthuizen.model;

import java.util.Arrays;

/**
 * A 2D array structure where each element represents a pixel as a char
 * An empty pixel is a space char ' '
 * A Raster must be constructed with a width and height > 0 otherwise an IllegalArgumentException will be thrown
 * When setPixel is called with x and/or y out of bound the operation will be ignored
 *
 * @author Andre Oosthuizen
 */
public class RasterDefault implements Raster {

    private static final char SPACE = ' ';
    private char[][] pixels;

    public RasterDefault(int width, int height) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be greater than zero");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be greater than zero");
        }
        this.pixels = new char[width][height];
        for (char[] row: pixels)
            Arrays.fill(row, SPACE);
    }

    @Override
    public int getWidth() {
        return pixels.length;
    }

    @Override
    public int getHeight() {
        return pixels[0].length;
    }

    @Override
    public char getPixel(int x, int y) {
        return pixels[x][y];
    }

    @Override
    public void setPixel(int x, int y, char c) {
        if (x >= 0 && x < getWidth() && y >=0 && y < getHeight()) {
            pixels[x][y] = c;
        }
    }

}