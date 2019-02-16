package com.andreoosthuizen.console;

import com.andreoosthuizen.controller.Controller;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DrawCornerCommandTest {

    @Test
    void should_ReturnFalse_When_InputIsEmptyString() {
        DrawCornerCommand drawCornerCommand = new DrawCornerCommand(null);
        assertFalse(drawCornerCommand.canExecute(""));
    }

    @Test
    void should_ReturnFalse_When_InputIsNull() {
        DrawCornerCommand drawCornerCommand = new DrawCornerCommand(null);
        assertFalse(drawCornerCommand.canExecute(null));
    }


    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithNoX1AndNoY1AndNoX2AndNoY2() {
        DrawCornerCommand drawCornerCommand = new DrawCornerCommand(null);
        assertFalse(drawCornerCommand.canExecute("L"));
    }

    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithX1AndNoY1AndNoX2AndNoY2() {
        DrawCornerCommand drawCornerCommand = new DrawCornerCommand(null);
        assertFalse(drawCornerCommand.canExecute("L 1"));
    }

    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithX1AndY1AndNoX2AndNoY2() {
        DrawCornerCommand drawCornerCommand = new DrawCornerCommand(null);
        assertFalse(drawCornerCommand.canExecute("L 1 2"));
    }

    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithX1AndY1AndX2AndNoY2() {
        DrawCornerCommand drawCornerCommand = new DrawCornerCommand(null);
        assertFalse(drawCornerCommand.canExecute("L 1 2 3"));
    }

    @Test
    void should_ReturnTrue_When_InputMatchesPattern() {
        DrawCornerCommand drawCornerCommand = new DrawCornerCommand(null);
        assertTrue(drawCornerCommand.canExecute("L 1 2 3 4"));
    }

    @Test
    void should_InvokeController_When_ExecuteWithInput() {
        Controller controller = mock(Controller.class);
        DrawCornerCommand drawCornerCommand = new DrawCornerCommand(controller);
        assertTrue(drawCornerCommand.execute("L 1 2 3 4"));
        verify(controller, times(1)).drawCorner(1, 2, 3, 4);
    }

}