package com.andreoosthuizen.controller;

import com.andreoosthuizen.model.Canvas;
import com.andreoosthuizen.model.Drawable;
import com.andreoosthuizen.model.Raster;
import com.andreoosthuizen.view.View;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DefaultControllerTest {

    @Test
    void should_CallCanvasResize_When_CreateCanvasRequestedWithDimensions() {
        Canvas canvas = mock(Canvas.class);
        View view = mock(View.class);
        DefaultController defaultController = new DefaultController(view, canvas);
        defaultController.createCanvas(1,1);
        verify(canvas, times(1)).initialise(1,1);
    }

    @Test
    void should_CallViewRender_When_CreateCanvasRequestedWithDimensions() {
        Canvas canvas = new Canvas();
        View view = mock(View.class);
        DefaultController defaultController = new DefaultController(view, canvas);
        defaultController.createCanvas(1,1);
        verify(view, times(1)).render(any(Raster.class));
    }

    @Test
    void should_CallCanvasPaintDrawable_When_DrawableProvided() {
        Canvas canvas = mock(Canvas.class);
        View view = mock(View.class);
        DefaultController defaultController = new DefaultController(view, canvas);
        Drawable drawable = mock(Drawable.class);
        defaultController.draw(drawable);
        verify(canvas, times(1)).draw(drawable);
    }

}