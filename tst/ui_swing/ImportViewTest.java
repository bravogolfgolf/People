package ui_swing;

import org.junit.Test;
import responder.View;

import static org.junit.Assert.assertEquals;

public class ImportViewTest {
    @Test
    public void shouldFormatRefreshViewModelForUi() {
        View view = new ImportView();
        String string = (String) view.generateView(1);
        assertEquals("1 record(s) imported", string);
    }
}