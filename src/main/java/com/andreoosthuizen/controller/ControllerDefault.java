package com.andreoosthuizen.controller;

import com.andreoosthuizen.model.Canvas;
import com.andreoosthuizen.model.Drawable;
import com.andreoosthuizen.model.Raster;
import com.andreoosthuizen.view.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Default implementation of our Controller interface
 *
 * @author Andre Oosthuizen
 */
public class ControllerDefault implements Controller, PropertyChangeListener {

    private View view;
    private Canvas canvas;

    public ControllerDefault(View view, Canvas canvas) {
        this.view = view;
        this.canvas = canvas;
        this.canvas.addPropertyChangeListener(this);
    }

    @Override
    public void createCanvas(int width, int height) {
        canvas.initialise(width, height);
    }

    @Override
    public void draw(Drawable... drawable) {
        canvas.draw(drawable);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        Raster raster = (Raster)propertyChangeEvent.getNewValue();
        view.render(raster);
    }

}
