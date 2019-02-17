package com.andreoosthuizen.model;

import java.beans.PropertyChangeListener;

public interface Canvas {

    void initialise(int width, int height);
    Raster getRaster();
    void draw(Drawable... drawables);
    void addPropertyChangeListener(PropertyChangeListener changeListener);

}
