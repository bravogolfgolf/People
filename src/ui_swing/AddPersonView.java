package ui_swing;

import responder.View;

public class AddPersonView implements View {
    @Override
    public Object generateView(Object object) {
        return String.format("Added Person with id = %d", (int) object);
    }
}