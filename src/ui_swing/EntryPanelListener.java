package ui_swing;

interface EntryPanelListener {
    void addEventEmitted(EntryPanelAddEvent e);

    void updateEventEmitted(EntryPanelUpdateEvent e);

    void deleteEventEmitted(EntryPanelDeleteEvent e);
}
