package ui;

import domain.PersonMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class PersonTablePanel extends JPanel {

    private final PersonTableModel personTableModel = new PersonTableModel();
    private final JTable tablePanel = new JTable(personTableModel);
    private final JPopupMenu popupMenu = new JPopupMenu();

    PersonTablePanel() {
        setupPopupMenu();
        addComponents();
    }

    private void setupPopupMenu() {
        JMenuItem deleteRowMenuItem = new JMenuItem("Delete row");
        popupMenu.add(deleteRowMenuItem);

        tablePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3)
                    popupMenu.show(tablePanel, e.getX(), e.getY());
            }
        });
    }

    private void addComponents() {
        setLayout(new BorderLayout());
        add(popupMenu);
        add(new JScrollPane(tablePanel), BorderLayout.CENTER);
    }

    void addDataForPersonTableModel(PersonMessage[] people) {
        personTableModel.addDataForPersonTableModel(people);
    }

    void refresh() {
        personTableModel.fireTableDataChanged();
    }
}
