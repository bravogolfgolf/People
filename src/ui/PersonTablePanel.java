package ui;

import domain.Person;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

class PersonTablePanel extends JPanel {

    private final PersonTableModel personTableModel = new PersonTableModel();

    PersonTablePanel() {
        setLayout(new BorderLayout());
        JTable tablePanel = new JTable(personTableModel);
        add(new JScrollPane(tablePanel), BorderLayout.CENTER);
    }

    void addDataForPersonTableModel(Map<Integer, Person> response) {
        personTableModel.addDataForPersonTableModel(response);
    }

    void refresh() {
        personTableModel.fireTableDataChanged();
    }
}
