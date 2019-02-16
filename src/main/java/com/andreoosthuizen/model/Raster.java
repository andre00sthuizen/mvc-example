package com.andreoosthuizen.model;

import java.util.Arrays;

/**
 * A 2D array structure where each element represents a pixel as a char
 * An empty pixel is a space char \u0012
 * A Raster must be constructed with a width and height > 0 otherwise an IllegalArgumentException will be thrown
 *
 * @author Andre Oosthuizen
 */
public class Raster {

    private static final char SPACE = ' ';
    private char[][] pixels;

    public Raster(int width, int height) {
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

    public char[][] getPixels() {
        return pixels;
    }

    public void setPixel(int x, int y, char c) {
        pixels[x][y] = c;
    }

}