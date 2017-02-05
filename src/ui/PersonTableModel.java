package ui;

import domain.PersonMessage;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PersonTableModel extends AbstractTableModel {

    private final Map<Integer, PersonMessage> personTableModelData = new HashMap<>();
    private final String[] columnNames = {"ID", "Full Name", "Occupation", "Age Category",
            "Employment Status", "US Citizen", "Tax ID", "Gender"};

    void addDataForPersonTableModel(PersonMessage[] response) {
        List<PersonMessage> list = Arrays.asList(response);
        personTableModelData.clear();
        for (PersonMessage message : list) {
            personTableModelData.put(message.id, message);
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return personTableModelData.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PersonMessage person = personTableModelData.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return person.id;
            case 1:
                return person.fullName;
            case 2:
                return person.occupation;
            case 3:
                return person.ageCategory;
            case 4:
                return person.employmentStatus;
            case 5:
                return person.uSCitizen;
            case 6:
                return person.taxId;
            case 7:
                return person.gender;
        }
        return null;
    }
}