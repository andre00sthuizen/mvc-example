package com.andreoosthuizen.controller;

import com.andreoosthuizen.model.Canvas;
import com.andreoosthuizen.model.Raster;
import com.andreoosthuizen.view.View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        Raster raster = (Raster)propertyChangeEvent.getNewValue();
        view.render(raster);
    }

}
