package com.andreoosthuizen.controller;

import com.andreoosthuizen.model.Canvas;
import com.andreoosthuizen.model.Raster;
import com.andreoosthuizen.view.View;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ControllerTest {

    @Test
    void should_CallCanvasResize_When_CreateCanvasRequestedWithDimensions() {
        Canvas canvas = mock(Canvas.class);
        View view = mock(View.class);
        Controller controller = new Controller(view, canvas);
        controller.createCanvas(1,1);
        verify(canvas, times(1)).resize(1,1);
    }

    @Test
    void should_CallViewRender_When_CreateCanvasRequestedWithDimensions() {
        Canvas canvas = new Canvas();
        View view = mock(View.class);
        Controller controller = new Controller(view, canvas);
        controller.createCanvas(1,1);
        verify(view, times(1)).render(any(Raster.class));
    }

}