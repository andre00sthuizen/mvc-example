package com.andreoosthuizen.controller;

import com.andreoosthuizen.model.Drawable;

public interface Controller {

    void createCanvas(int width, int height);
    void draw(Drawable... drawable);

}
