package com.andreoosthuizen.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LineTest {

    @Test
    void should_ThrowIllegalArgumentException_When_NotCreatedHorizontalOrVertical() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Line(1, 2, 3, 4, 'x');
        });
    }

    @Test
    void should_NotThrowIllegalArgumentException_When_CreatedHorizontal() {
        new Line(1, 2, 1, 3, 'x');
    }

    @Test
    void should_NotThrowIllegalArgumentException_When_CreatedVertical() {
        new Line(1, 2, 3, 2, 'x');
    }

}
