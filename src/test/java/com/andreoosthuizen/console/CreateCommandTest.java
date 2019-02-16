package com.andreoosthuizen.console;

import com.andreoosthuizen.controller.Controller;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCommandTest {

    @Test
    void should_ReturnFalse_When_InputIsEmptyString() {
        CreateCommand createCommand = new CreateCommand(null);
        assertFalse(createCommand.canExecute(""));
    }

    @Test
    void should_ReturnFalse_When_InputIsNull() {
        CreateCommand createCommand = new CreateCommand(null);
        assertFalse(createCommand.canExecute(null));
    }


    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithNoWidthAndHeight() {
        CreateCommand createCommand = new CreateCommand(null);
        assertFalse(createCommand.canExecute("C"));
    }

    @Test
    void should_ReturnFalse_When_InputMatchesFirstLetterWithWidthAndNoHeight() {
        CreateCommand createCommand = new CreateCommand(null);
        assertFalse(createCommand.canExecute("C 1"));
    }

    @Test
    void should_ReturnTrue_When_InputMatchesPattern() {
        CreateCommand createCommand = new CreateCommand(null);
        assertTrue(createCommand.canExecute("C 1 1"));
    }

    @Test
    void should_InvokeController_When_ExecuteWithInput() {
        Controller controller = mock(Controller.class);
        CreateCommand createCommand = new CreateCommand(controller);
        assertTrue(createCommand.execute("C 1 1"));
        verify(controller, times(1)).createCanvas(1, 1);
    }

}