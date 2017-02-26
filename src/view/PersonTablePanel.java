package view;

import other.View;
import responder.PersonRecord;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

class PersonTablePanel extends JPanel implements View {

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

    private void createAndAddPopUp() {
        deleteRowMenuItem.addActionListener(e -> {
            int viewRowSelected = tablePanel.getSelectedRow();
            int modelRowIndex = tablePanel.convertRowIndexToModel(viewRowSelected);
            int id = personTableModel.getIdOfPersonOn(modelRowIndex);
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

    @Override
    public PersonTableModelRecord[] generateView(PersonRecord[] records) {
        PersonTableModelRecord[] model = new PersonTableModelRecord[records.length];
        int i = 0;
        for (PersonRecord record : records)
            model[i++] = addRecord(record);
        return model;
    }

    private PersonTableModelRecord addRecord(PersonRecord pr) {
        PersonTableModelRecord modelRecord = new PersonTableModelRecord();
        modelRecord.id = pr.id;
        modelRecord.fullName = pr.fullName;
        modelRecord.occupation = pr.occupation;
        modelRecord.ageCategory = pr.ageCategory;
        modelRecord.employmentStatus = pr.employmentStatus;
        modelRecord.uSCitizen = pr.uSCitizen;
        modelRecord.taxId = pr.taxId;
        modelRecord.gender = pr.gender;
        return modelRecord;
    }

    void updateModel(PersonTableModelRecord[] records) {
        personTableModel.addDataForPersonTableModel(records);
        personTableModel.fireTableDataChanged();
    }
}
