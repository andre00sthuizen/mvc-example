package com.andreoosthuizen.command;

import com.andreoosthuizen.controller.Controller;
import com.andreoosthuizen.controller.ControllerDefault;
import com.andreoosthuizen.model.Line;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DrawSquareCommandTest {

    @Test
    void should_ReturnFalse_When_InputIsEmptyString() {
        DrawRectangleCommand drawRectangleCommand = new DrawRectangleCommand();
        assertFalse(drawRectangleCommand.canExecute(""));
    }

    @Test
    void should_ReturnFalse_When_InputIsNull() {
        DrawRectangleCommand drawRectangleCommand = new DrawRectangleCommand();
        assertFalse(drawRectangleCommand.canExecute(null));
    }

    @Test
    void should_ReturnTrue_When_WrongInputExecuted() {
        DrawRectangleCommand drawRectangleCommand = new DrawRectangleCommand();
        assertTrue(drawRectangleCommand.execute("invalid", null));
    }

    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithNoX1AndNoY1AndNoX2AndNoY2() {
        DrawRectangleCommand drawRectangleCommand = new DrawRectangleCommand();
        assertFalse(drawRectangleCommand.canExecute("R"));
    }

    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithX1AndNoY1AndNoX2AndNoY2() {
        DrawRectangleCommand drawRectangleCommand = new DrawRectangleCommand();
        assertFalse(drawRectangleCommand.canExecute("R 1"));
    }

    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithX1AndY1AndNoX2AndNoY2() {
        DrawRectangleCommand drawRectangleCommand = new DrawRectangleCommand();
        assertFalse(drawRectangleCommand.canExecute("R 1 2"));
    }

    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithX1AndY1AndX2AndNoY2() {
        DrawRectangleCommand drawRectangleCommand = new DrawRectangleCommand();
        assertFalse(drawRectangleCommand.canExecute("R 1 2 3"));
    }

    @Test
    void should_ReturnTrue_When_InputMatchesPattern() {
        DrawRectangleCommand drawRectangleCommand = new DrawRectangleCommand();
        assertTrue(drawRectangleCommand.canExecute("R 1 2 3 4"));
    }

    @Test
    void should_InvokeControllerWith4LineDrawables_When_ExecuteWithInput() {
        Controller controller = mock(ControllerDefault.class);
        DrawRectangleCommand drawRectangleCommand = new DrawRectangleCommand();
        assertTrue(drawRectangleCommand.execute("R 1 2 3 4", controller));
        Line top = new Line(1, 2, 3, 2, 'x');
        Line bottom = new Line(1, 4, 3, 4, 'x');
        Line left = new Line(1, 2, 1, 4, 'x');
        Line right = new Line(3, 2, 3, 4, 'x');
        verify(controller, times(1)).draw(top, bottom, left, right);
    }

}