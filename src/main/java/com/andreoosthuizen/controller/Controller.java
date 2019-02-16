package com.andreoosthuizen.controller;

import com.andreoosthuizen.model.Canvas;
import com.andreoosthuizen.model.Raster;
import com.andreoosthuizen.view.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Andre Oosthuizen
 */
public class Controller implements PropertyChangeListener {

    private View view;
    private Canvas canvas;

    public Controller(View view, Canvas canvas) {
        this.view = view;
        this.canvas = canvas;
        this.canvas.addPropertyChangeListener(this);
    }

    public void createCanvas(int width, int height) {
        canvas.resize(width, height);
    }

    public void drawCorner(int x1, int y1, int x2, int y2) {
        //TODO
    }

    public void drawRectangle(int x1, int y1, int x2, int y2) {
        //TODO
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        Raster raster = (Raster)propertyChangeEvent.getNewValue();
        view.render(raster);
    }

}
