package com.andreoosthuizen.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * Draws a framed rectangular shape with borders as indicated below
 * e.g. width=20 and height=4 renders as follow:
 * <pre>
 * ----------------------
 * |                    |
 * |                    |
 * |                    |
 * |                    |
 * ----------------------
 * </pre>
 *
 * The width and height default to 10.
 * Call resize(int width, int height) to change it.
 *
 * @author Andre Oosthuizen
 */
public class Canvas implements Drawable {

    private PropertyChangeSupport propertyChanges = new PropertyChangeSupport(this);
    private int width = 10;
    private int height = 10;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void resize(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Canvas width/height must not be less than zero");
        }
        this.width = width;
        this.height = height;
        this.propertyChanges.firePropertyChange(new PropertyChangeEvent(this, "resize",null , paint()));
    }

    public void addPropertyChangeListener(PropertyChangeListener changeListener) {
        this.propertyChanges.addPropertyChangeListener(changeListener);
    }

    @Override
    public Raster paint() {
        int width = getWidth() + 2;
        int height = getHeight() + 2;
        Raster raster = new Raster(width, height);
        char[][] pixels = raster.getPixels();
        //Paint top and bottom rows
        for (int i=0; i<width; i++) {
            raster.setPixel(i, 0, '-');
            raster.setPixel(i, height - 1, '-');
        }
        //Paint left and right cols
        for (int j=1; j<height -1; j++) {
            raster.setPixel(0, j, '|');
            raster.setPixel(width-1, j, '|');
        }
        return raster;
    }

    PropertyChangeListener[] getPropertyChangeListeners() {
        return propertyChanges.getPropertyChangeListeners();
    }

}
