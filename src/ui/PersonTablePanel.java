package ui;

import domain.AddPersonRequest;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

class PersonTablePanel extends JPanel {

    private final PersonTableModel personTableModel = new PersonTableModel();
    private final JTable tablePanel = new JTable(personTableModel);
    private final JPopupMenu popupMenu = new JPopupMenu();
    private final JMenuItem deleteRowMenuItem = new JMenuItem("Delete row");
    private final PersonTablePanelListener personTablePanelListener;

    PersonTablePanel(PersonTablePanelListener personTablePanelListener) {
        this.personTablePanelListener = personTablePanelListener;
        setLayout(new BorderLayout());
        createAndAddPopUp();
        createAndAddTablePanel();
    }

    void addDataForPersonTableModel(AddPersonRequest[] people) {
        personTableModel.addDataForPersonTableModel(people);
    }

    void refresh() {
        personTableModel.fireTableDataChanged();
    }

    private void createAndAddPopUp() {
        deleteRowMenuItem.addActionListener(e -> {
            int rowSelected = tablePanel.getSelectedRow();
            int id = personTableModel.getIdOfPersonOn(rowSelected);
            personTablePanelListener.personDeleted(id);
        });
        popupMenu.add(deleteRowMenuItem);
        add(popupMenu);
    }

    private void createAndAddTablePanel() {
        tablePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowSelected = tablePanel.rowAtPoint(e.getPoint());
                tablePanel.setRowSelectionInterval(rowSelected, rowSelected);
                if (e.getButton() == MouseEvent.BUTTON3)
                    popupMenu.show(tablePanel, e.getX(), e.getY());
            }
        });
        TableRowSorter<PersonTableModel> sorter = new TableRowSorter<>(personTableModel);
        tablePanel.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        add(new JScrollPane(tablePanel), BorderLayout.CENTER);
    }
}
