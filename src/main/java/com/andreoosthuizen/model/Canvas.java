package com.andreoosthuizen.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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
 * Call initialise(int width, int height) to change it.
 *
 * @author Andre Oosthuizen
 */
public class Canvas implements Drawable {

    private PropertyChangeSupport propertyChanges = new PropertyChangeSupport(this);
    private Raster raster;

    public void initialise(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Canvas width/height must not be less than zero");
        }
        this.raster = new Raster(width + 2, height + 2);
        paint(this.raster);
        this.propertyChanges.firePropertyChange(new PropertyChangeEvent(this, "raster",null, this.raster));
    }

    @Override
    public void paint(Raster raster) {
        //Paint top and bottom rows
        for (int i=0; i<raster.getWidth(); i++) {
            raster.setPixel(i, 0, '-');
            raster.setPixel(i, raster.getHeight() - 1, '-');
        }
        //Paint left and right cols
        for (int j=1; j<raster.getHeight() -1; j++) {
            raster.setPixel(0, j, '|');
            raster.setPixel(raster.getWidth() - 1, j, '|');
        }
    }

    public int getWidth() {
        if (raster == null) {
            return 0;
        }
        return raster.getWidth() - 2;
    }

    public int getHeight() {
        if (raster == null) {
            return 0;
        }
        return raster.getHeight() - 2;
    }

    public Raster getRaster() {
        return raster;
    }

    public void draw(Drawable... drawables) {
        if (drawables != null) {
            for (Drawable drawable: drawables) {
                drawable.paint(this.raster);
            }
            this.propertyChanges.firePropertyChange(new PropertyChangeEvent(this, "raster",null, this.raster));
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener changeListener) {
        this.propertyChanges.addPropertyChangeListener(changeListener);
    }

    PropertyChangeListener[] getPropertyChangeListeners() {
        return propertyChanges.getPropertyChangeListeners();
    }

}
