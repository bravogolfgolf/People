package ui_swing;

import responder.View;

public class DeletePersonView implements View {
    @Override
    public Object generateView(Object object) {
        return String.format("Deleted Person with id = %d", (int) object);
    }
}
