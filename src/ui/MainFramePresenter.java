package ui;

import domain.PersonMessage;

public interface MainFramePresenter {
    void updatePersonTableModel(PersonMessage[] response);
}
