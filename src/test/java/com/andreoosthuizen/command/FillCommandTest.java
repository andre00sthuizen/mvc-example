package com.andreoosthuizen.command;

import com.andreoosthuizen.controller.Controller;
import com.andreoosthuizen.controller.DefaultController;
import com.andreoosthuizen.model.Fill;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class FillCommandTest {

    @Test
    void should_ReturnFalse_When_InputIsEmptyString() {
        FillCommand fillCommand = new FillCommand();
        assertFalse(fillCommand.canExecute(""));
    }

    @Test
    void should_ReturnFalse_When_InputIsNull() {
        FillCommand fillCommand = new FillCommand();
        assertFalse(fillCommand.canExecute(null));
    }

    @Test
    void should_ReturnTrue_When_WrongInputExecuted() {
        FillCommand fillCommand = new FillCommand();
        assertTrue(fillCommand.execute("invalid", null));
    }

    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithNoX1AndNoY1AndNoChar() {
        FillCommand fillCommand = new FillCommand();
        assertFalse(fillCommand.canExecute("B"));
    }

    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithX1AndNoY1AndNoChar() {
        FillCommand fillCommand = new FillCommand();
        assertFalse(fillCommand.canExecute("B 1"));
    }

    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithX1AndY1AndNoChar() {
        FillCommand fillCommand = new FillCommand();
        assertFalse(fillCommand.canExecute("B 1 2"));
    }

    @Test
    void should_ReturnTrue_When_InputMatchesPattern() {
        FillCommand fillCommand = new FillCommand();
        assertTrue(fillCommand.canExecute("B 1 2 x"));
    }

    @Test
    void should_InvokeControllerWithLineDrawable_When_ExecuteWithInput() {
        Controller controller = mock(DefaultController.class);
        FillCommand fillCommand = new FillCommand();
        assertTrue(fillCommand.execute("B 1 2 o", controller));
        Fill fill = new Fill(1, 2, 'o');
        verify(controller, times(1)).draw(fill);
    }

}