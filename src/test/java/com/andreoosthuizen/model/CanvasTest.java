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
    void should_DefaultWidthTo10_When_CreatedWithoutWidth() {
        Canvas canvas = new Canvas();
        assertEquals(10, canvas.getWidth());
    }

    @Test
    void should_DefaultHeightTo10_When_CreatedWithoutHeight() {
        Canvas canvas = new Canvas();
        assertEquals(10, canvas.getHeight());
    }

    @Test
    void should_ReturnWidth2AndHeight2_When_ResizedToZeroDimensions() {
        Canvas canvas = new Canvas();
        canvas.resize(0, 0);
        assertEquals(0, canvas.getWidth());
        assertEquals(0, canvas.getHeight());
    }

    @Test
    void should_ReturnUpdatedWidthAndHeight_When_ResizedToPositiveDimensions() {
        Canvas canvas = new Canvas();
        canvas.resize(15, 30);
        assertEquals(15, canvas.getWidth());
        assertEquals(30, canvas.getHeight());
    }

    @ParameterizedTest
    @CsvSource({ "-1,1", "1,-1", "-1,-1"})
    void should_ThrowIllegalArgumentException_When_ResizedToZeroOrLessDimensions(int width, int height) {
        Canvas canvas = new Canvas();
        assertThrows(IllegalArgumentException.class, () -> {
            canvas.resize(width, height);
        }, "Expected IllegalArgumentException resizing to negative dimensions");
    }

    @Test
    void should_Return12By12Array_When_PaintWithoutResize() {
        Canvas canvas = new Canvas();
        Raster raster = canvas.paint();
        for (int i=0; i<12; i++) {
            assertEquals(12, raster.getPixels()[i].length);
        }
    }

    @Test
    void should_Return2By2Raster_When_PaintWithZeroResize() {
        Canvas canvas = new Canvas();
        canvas.resize(0, 0);
        Raster raster = canvas.paint();
        for (int i=0; i<2; i++) {
            assertEquals(2, raster.getPixels()[i].length);
        }
    }

    @Test
    void should_ReturnRaster_When_PaintWithPositiveResize() {
        Canvas canvas = new Canvas();
        canvas.resize(7, 13);
        Raster raster = canvas.paint();
        for (int i=0; i<9; i++) {
            assertEquals(15, raster.getPixels()[i].length);
        }
    }

    @Test
    void should_PaintTopBorderWithDash_When_Paint() {
        Canvas canvas = new Canvas();
        Raster raster = canvas.paint();
        for (int i=0; i<12; i++) {
            assertEquals('-', raster.getPixels()[i][0]);
        }
    }

    @Test
    void should_PaintBottomBorderWithDash_When_Paint() {
        Canvas canvas = new Canvas();
        Raster raster = canvas.paint();
        for (int i=0; i<12; i++) {
            assertEquals('-', raster.getPixels()[i][11]);
        }
    }

    @Test
    void should_PaintLeftBorderWithPipe_When_Paint() {
        Canvas canvas = new Canvas();
        Raster raster = canvas.paint();
        for (int j=1; j<11; j++) {
            assertEquals('|', raster.getPixels()[0][j]);
        }
    }

    @Test
    void should_PaintRightBorderWithPipe_When_Paint() {
        Canvas canvas = new Canvas();
        Raster raster = canvas.paint();
        for (int j=1; j<11; j++) {
            assertEquals('|', raster.getPixels()[11][j]);
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
    void should_FirePropertyChangeEventOnce_When_Resize() {
        Canvas canvas = new Canvas();
        PropertyChangeListener changeListener = mock(PropertyChangeListener.class);
        canvas.resize(1, 1);
        verify(changeListener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }

    @Test
    void should_FirePropertyChangeEventWithOldAndNewDimensions_When_Resize() {
        Canvas canvas = new Canvas();
        PropertyChangeListener changeListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                assertEquals(new int[] {10, 10}, propertyChangeEvent.getOldValue());
            }
        };
        canvas.resize(1, 1);
    }

}
