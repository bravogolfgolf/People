package ui_swing;

import responder.View;

public class ExportView implements View {
    @Override
    public Object generateView(Object object) {
        return String.format("%d record(s) exported", (int) object);
    }
}