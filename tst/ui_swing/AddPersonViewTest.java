package ui_swing;

import org.junit.Test;
import responder.View;

import static org.junit.Assert.assertEquals;

public class AddPersonViewTest {

    @Test
    public void shouldFormatRefreshViewModelForUi() {
        View view = new AddPersonView();
        String string = (String) view.generateView(1);
        assertEquals("Added Person with id = 1", string);

    }
}