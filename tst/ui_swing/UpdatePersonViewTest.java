package ui_swing;

import org.junit.Test;
import responder.View;

import static org.junit.Assert.assertEquals;

public class UpdatePersonViewTest {
    @Test
    public void shouldFormatRefreshViewModelForUi() {
        View view = new UpdatePersonView();
        String string = (String) view.generateView(1);
        assertEquals("Updated Person with id = 1", string);
    }
}
