package ui_swing;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

class PersonTablePanel extends JPanel {

    private final PersonTableModel personTableModel = new PersonTableModel();
    private final JTable tablePanel = new JTable(personTableModel);
    private final PersonTablePanelListener personTablePanelListener;

    PersonTablePanel(PersonTablePanelListener personTablePanelListener) {
        this.personTablePanelListener = personTablePanelListener;
        setLayout(new BorderLayout());
        createAndAddTablePanel();
    }

    private void createAndAddTablePanel() {
        tablePanel.setSelectionModel(new DefaultListSelectionModel() {
            {
                setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            }
        });

        tablePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowSelected = tablePanel.rowAtPoint(e.getPoint());
                tablePanel.setRowSelectionInterval(rowSelected, rowSelected);
                int viewRowSelected = tablePanel.getSelectedRow();
                int modelRowIndex = tablePanel.convertRowIndexToModel(viewRowSelected);
                personTablePanelListener.rowSelected(personTableModel.getIdOfPersonOn(modelRowIndex), personTableModel.getFullNameOfPersonOn(modelRowIndex), personTableModel.getOccupationOfPersonOn(modelRowIndex), personTableModel.getAgeCategoryOfPersonOn(modelRowIndex), personTableModel.getEmploymentStatusOfPersonOn(modelRowIndex), personTableModel.getUsCitizenOfPersonOn(modelRowIndex), personTableModel.getTaxIdOfPersonOn(modelRowIndex), personTableModel.getGenderOfPersonOn(modelRowIndex));
            }
        });

        tablePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int viewRowSelected = tablePanel.getSelectedRow();
                int modelRowIndex = tablePanel.convertRowIndexToModel(viewRowSelected);
                personTablePanelListener.rowSelected(personTableModel.getIdOfPersonOn(modelRowIndex), personTableModel.getFullNameOfPersonOn(modelRowIndex), personTableModel.getOccupationOfPersonOn(modelRowIndex), personTableModel.getAgeCategoryOfPersonOn(modelRowIndex), personTableModel.getEmploymentStatusOfPersonOn(modelRowIndex), personTableModel.getUsCitizenOfPersonOn(modelRowIndex), personTableModel.getTaxIdOfPersonOn(modelRowIndex), personTableModel.getGenderOfPersonOn(modelRowIndex));
            }
        });

        TableRowSorter<PersonTableModel> sorter = new TableRowSorter<>(personTableModel);
        tablePanel.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        add(new JScrollPane(tablePanel), BorderLayout.CENTER);
    }

    void updateModel(PersonTableModelRecord[] records) {
        personTableModel.addDataForPersonTableModel(records);
        personTableModel.fireTableDataChanged();
    }

}
