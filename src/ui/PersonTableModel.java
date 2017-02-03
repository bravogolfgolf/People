package ui;

import domain.Person;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.Map;

class PersonTableModel extends AbstractTableModel {

    private Map<Integer,Person> personTableModelData = new HashMap<>();
    private final String[] columnNames = {"ID", "Full Name", "Occupation", "Age Category",
            "Employment Status", "US Citizen", "Tax ID", "Gender"};

    void addDataForPersonTableModel(Map<Integer, Person> personTableModelData) {
        this.personTableModelData = personTableModelData;
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
        Person person = personTableModelData.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return person.getId();
            case 1:
                return person.getFullName();
            case 2:
                return person.getOccupation();
            case 3:
                return person.getAgeCategory();
            case 4:
                return person.getEmploymentStatus();
            case 5:
                return person.isUsCitizen();
            case 6:
                return person.getTaxId();
            case 7:
                return person.getGender();
        }
        return null;
    }
}