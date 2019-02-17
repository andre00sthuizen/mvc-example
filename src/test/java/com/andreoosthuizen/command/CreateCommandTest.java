package com.andreoosthuizen.command;

import com.andreoosthuizen.controller.Controller;
import com.andreoosthuizen.controller.DefaultController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class CreateCommandTest {

    @Test
    void should_ReturnFalse_When_InputIsEmptyString() {
        CreateCommand createCommand = new CreateCommand();
        assertFalse(createCommand.canExecute(""));
    }

    @Test
    void should_ReturnFalse_When_InputIsNull() {
        CreateCommand createCommand = new CreateCommand();
        assertFalse(createCommand.canExecute(null));
    }

    @Test
    void should_ReturnTrue_When_WrongInputExecuted() {
        CreateCommand createCommand = new CreateCommand();
        assertTrue(createCommand.execute("invalid", null));
    }

    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithNoWidthAndHeight() {
        CreateCommand createCommand = new CreateCommand();
        assertFalse(createCommand.canExecute("C"));
    }

    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithWidthAndNoHeight() {
        CreateCommand createCommand = new CreateCommand();
        assertFalse(createCommand.canExecute("C 1"));
    }

    @Test
    void should_ReturnTrue_When_InputMatchesPattern() {
        CreateCommand createCommand = new CreateCommand();
        assertTrue(createCommand.canExecute("C 1 1"));
    }

    @Test
    void should_InvokeController_When_ExecuteWithInput() {
        Controller controller = mock(DefaultController.class);
        CreateCommand createCommand = new CreateCommand();
        assertTrue(createCommand.execute("C 1 1", controller));
        verify(controller, times(1)).createCanvas(1, 1);
    }

}