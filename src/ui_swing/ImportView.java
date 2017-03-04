package ui_swing;

import responder.View;

public class ImportView implements View {
    @Override
    public Object generateView(Object object) {
        return String.format("%d record(s) imported", (int) object);
    }
}