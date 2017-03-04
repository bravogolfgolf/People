package ui_swing;

import org.junit.Test;
import responder.View;

import static org.junit.Assert.assertEquals;

public class DeletePersonViewTest {
    @Test
    public void shouldFormatRefreshViewModelForUi() {
        View view = new DeletePersonView();
        String string = (String) view.generateView(1);
        assertEquals("Deleted Person with id = 1", string);
    }
}
