package ui_swing;

import responder.View;

public class UpdatePersonView implements View {
    @Override
    public Object generateView(Object object) {
        return String.format("Updated Person with id = %d", (int) object);

    }
}
