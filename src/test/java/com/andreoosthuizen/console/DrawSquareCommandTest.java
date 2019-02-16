package com.andreoosthuizen.console;

import com.andreoosthuizen.controller.Controller;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DrawSquareCommandTest {

    @Test
    void should_ReturnFalse_When_InputIsEmptyString() {
        DrawRectangleCommand drawRectangleCommand = new DrawRectangleCommand(null);
        assertFalse(drawRectangleCommand.canExecute(""));
    }

    @Test
    void should_ReturnFalse_When_InputIsNull() {
        DrawRectangleCommand drawRectangleCommand = new DrawRectangleCommand(null);
        assertFalse(drawRectangleCommand.canExecute(null));
    }


    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithNoX1AndNoY1AndNoX2AndNoY2() {
        DrawRectangleCommand drawRectangleCommand = new DrawRectangleCommand(null);
        assertFalse(drawRectangleCommand.canExecute("R"));
    }

    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithX1AndNoY1AndNoX2AndNoY2() {
        DrawRectangleCommand drawRectangleCommand = new DrawRectangleCommand(null);
        assertFalse(drawRectangleCommand.canExecute("R 1"));
    }

    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithX1AndY1AndNoX2AndNoY2() {
        DrawRectangleCommand drawRectangleCommand = new DrawRectangleCommand(null);
        assertFalse(drawRectangleCommand.canExecute("R 1 2"));
    }

    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithX1AndY1AndX2AndNoY2() {
        DrawRectangleCommand drawRectangleCommand = new DrawRectangleCommand(null);
        assertFalse(drawRectangleCommand.canExecute("R 1 2 3"));
    }

    @Test
    void should_ReturnTrue_When_InputMatchesPattern() {
        DrawRectangleCommand drawRectangleCommand = new DrawRectangleCommand(null);
        assertTrue(drawRectangleCommand.canExecute("R 1 2 3 4"));
    }

    @Test
    void should_InvokeController_When_ExecuteWithInput() {
        Controller controller = mock(Controller.class);
        DrawRectangleCommand drawRectangleCommand = new DrawRectangleCommand(controller);
        assertTrue(drawRectangleCommand.execute("R 1 2 3 4"));
        verify(controller, times(1)).drawRectangle(1, 2, 3, 4);
    }

}