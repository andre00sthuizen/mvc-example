package com.andreoosthuizen.console;

import com.andreoosthuizen.controller.Controller;
import com.andreoosthuizen.model.Line;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DrawLineCommandTest {

    @Test
    void should_ReturnFalse_When_InputIsEmptyString() {
        DrawLineCommand drawLineCommand = new DrawLineCommand(null);
        assertFalse(drawLineCommand.canExecute(""));
    }

    @Test
    void should_ReturnFalse_When_InputIsNull() {
        DrawLineCommand drawLineCommand = new DrawLineCommand(null);
        assertFalse(drawLineCommand.canExecute(null));
    }


    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithNoX1AndNoY1AndNoX2AndNoY2() {
        DrawLineCommand drawLineCommand = new DrawLineCommand(null);
        assertFalse(drawLineCommand.canExecute("L"));
    }

    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithX1AndNoY1AndNoX2AndNoY2() {
        DrawLineCommand drawLineCommand = new DrawLineCommand(null);
        assertFalse(drawLineCommand.canExecute("L 1"));
    }

    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithX1AndY1AndNoX2AndNoY2() {
        DrawLineCommand drawLineCommand = new DrawLineCommand(null);
        assertFalse(drawLineCommand.canExecute("L 1 2"));
    }

    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithX1AndY1AndX2AndNoY2() {
        DrawLineCommand drawLineCommand = new DrawLineCommand(null);
        assertFalse(drawLineCommand.canExecute("L 1 2 3"));
    }

    @Test
    void should_ReturnTrue_When_InputMatchesPattern() {
        DrawLineCommand drawLineCommand = new DrawLineCommand(null);
        assertTrue(drawLineCommand.canExecute("L 1 2 3 4"));
    }

    @Test
    void should_InvokeControllerWithLineDrawable_When_ExecuteWithInput() {
        Controller controller = mock(Controller.class);
        DrawLineCommand drawLineCommand = new DrawLineCommand(controller);
        assertTrue(drawLineCommand.execute("L 1 2 1 5"));
        Line line = new Line(1, 2, 1, 5, 'x');
        verify(controller, times(1)).paintDrawable(line);
    }

}