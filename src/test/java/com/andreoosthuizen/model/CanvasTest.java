package com.andreoosthuizen.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CanvasTest {


    @Test
    void should_DefaultHeightTo0_When_CreatedWithoutInitialise() {
        Canvas canvas = new Canvas();
        assertEquals(0, canvas.getHeight());
    }

    @Test
    void should_DefaultWidthTo0_When_CreatedWithoutInitialise() {
        Canvas canvas = new Canvas();
        assertEquals(0, canvas.getWidth());
    }

    @Test
    void should_ReturnWidth0AndHeight0_When_IntialiseToZeroDimensions() {
        Canvas canvas = new Canvas();
        canvas.initialise(0, 0);
        assertEquals(0, canvas.getWidth());
        assertEquals(0, canvas.getHeight());
    }

    @Test
    void should_ReturnUpdatedWidthAndHeight_When_IntialiseToPositiveDimensions() {
        Canvas canvas = new Canvas();
        canvas.initialise(15, 30);
        assertEquals(15, canvas.getWidth());
        assertEquals(30, canvas.getHeight());
    }

    @ParameterizedTest
    @CsvSource({ "-1,1", "1,-1", "-1,-1"})
    void should_ThrowIllegalArgumentException_When_IntialiseToZeroOrLessDimensions(int width, int height) {
        Canvas canvas = new Canvas();
        assertThrows(IllegalArgumentException.class, () -> {
            canvas.initialise(width, height);
        }, "Expected IllegalArgumentException resizing to negative dimensions");
    }

    @Test
    void should_CreateRasterWithFrameWidthsIncluded_When_InitialiseWithValidDimensions() {
        Canvas canvas = new Canvas();
        canvas.initialise(7, 13);
        assertEquals(9, canvas.getRaster().getWidth());
        assertEquals(15, canvas.getRaster().getHeight());
    }

    @Test
    void should_PaintTopBorderWithDash_When_Paint() {
        Canvas canvas = new Canvas();
        canvas.initialise(10, 10);
        for (int i=0; i<12; i++) {
            assertEquals('-', canvas.getRaster().getPixel(i, 0));
        }
    }

    @Test
    void should_PaintBottomBorderWithDash_When_Paint() {
        Canvas canvas = new Canvas();
        canvas.initialise(10, 10);
        for (int i=0; i<12; i++) {
            assertEquals('-', canvas.getRaster().getPixel(i, 11));
        }
    }

    @Test
    void should_PaintLeftBorderWithPipe_When_Paint() {
        Canvas canvas = new Canvas();
        canvas.initialise(10, 10);
        for (int j=1; j<11; j++) {
            assertEquals('|', canvas.getRaster().getPixel(0, j));
        }
    }

    @Test
    void should_PaintRightBorderWithPipe_When_Paint() {
        Canvas canvas = new Canvas();
        canvas.initialise(10, 10);
        canvas.initialise(10, 10);
        for (int j=1; j<11; j++) {
            assertEquals('|', canvas.getRaster().getPixel(11, j));
        }
    }

    @Test
    void should_ReturnZeroPropertyChangeListeners_When_GetPropertyChangeListeners() {
        Canvas canvas = new Canvas();
        assertEquals(0, canvas.getPropertyChangeListeners().length);
    }

    @Test
    void should_AllowPropertyChangeListener_When_AddPropertyChangeListener() {
        Canvas canvas = new Canvas();
        PropertyChangeListener changeListener = mock(PropertyChangeListener.class);
        canvas.addPropertyChangeListener(changeListener);
        assertEquals(changeListener, canvas.getPropertyChangeListeners()[0]);
    }

    @Test
    void should_FirePropertyChangeEventOnce_When_Initialise() {
        Canvas canvas = new Canvas();
        PropertyChangeListener changeListener = mock(PropertyChangeListener.class);
        canvas.addPropertyChangeListener(changeListener);
        canvas.initialise(1, 1);
        verify(changeListener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }

    @Test
    void should_NotFirePropertyChangeEvent_When_DrawWithNullDrawables() {
        Canvas canvas = new Canvas();
        PropertyChangeListener changeListener = mock(PropertyChangeListener.class);
        canvas.addPropertyChangeListener(changeListener);
        canvas.initialise(1, 1);
        verify(changeListener, times(1)).propertyChange(any(PropertyChangeEvent.class));
        canvas.draw((Drawable[]) null);
        verifyNoMoreInteractions(changeListener);
    }

    @Test
    void should_FirePropertyChangeEventOnce_When_DrawWithMultipleDrawables() {
        Canvas canvas = new Canvas();
        Drawable drawable = mock(Drawable.class);
        PropertyChangeListener changeListener = mock(PropertyChangeListener.class);
        canvas.addPropertyChangeListener(changeListener);
        canvas.initialise(1, 1);
        canvas.draw(drawable, drawable, drawable);
        verify(changeListener, times(2)).propertyChange(any(PropertyChangeEvent.class));
    }

}
