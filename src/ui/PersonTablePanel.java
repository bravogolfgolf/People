package ui;

import domain.PersonMessage;

import javax.swing.*;
import java.awt.*;

class PersonTablePanel extends JPanel {

    private final PersonTableModel personTableModel = new PersonTableModel();

    PersonTablePanel() {
        setLayout(new BorderLayout());
        JTable tablePanel = new JTable(personTableModel);
        add(new JScrollPane(tablePanel), BorderLayout.CENTER);
    }

    void addDataForPersonTableModel(PersonMessage[] people) {
        personTableModel.addDataForPersonTableModel(people);
    }

    void refresh() {
        personTableModel.fireTableDataChanged();
    }
}
