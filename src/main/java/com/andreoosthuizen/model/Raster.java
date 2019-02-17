package com.andreoosthuizen.model;

/**
 * A 2D array structure where each element represents a pixel as a char
 * An empty pixel is a space char ' '
 * A Raster must be constructed with a width and height > 0 otherwise an IllegalArgumentException will be thrown
 * When setPixel is called with x and/or y out of bound the operation will be ignored
 *
 * @author Andre Oosthuizen
 */
public interface Raster {

    int getWidth();
    int getHeight();
    char getPixel(int x, int y);
    void setPixel(int x, int y, char c);

}
