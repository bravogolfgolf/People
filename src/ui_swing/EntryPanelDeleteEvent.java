package ui_swing;

import java.util.EventObject;

class EntryPanelDeleteEvent extends EventObject {
    final int id;

    EntryPanelDeleteEvent(Object source, int id) {
        super(source);
        this.id = id;
    }
}
