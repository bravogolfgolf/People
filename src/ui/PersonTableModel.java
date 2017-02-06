package ui;

import domain.PersonMessage;

import javax.swing.table.AbstractTableModel;

class PersonTableModel extends AbstractTableModel {

    private PersonMessage[] personTableModelData;
    private final String[] columnNames = {"ID", "Full Name", "Occupation", "Age Category",
            "Employment Status", "US Citizen", "Tax ID", "Gender"};

    void addDataForPersonTableModel(PersonMessage[] people) {
        personTableModelData = new PersonMessage[people.length];
        personTableModelData = people;
    }

    int getIdOfPersonOn(int rowNumber) {
        return (int) getValueAt(rowNumber, 0);
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Integer.class;
            case 4:
                return Integer.class;
            case 5:
                return Boolean.class;
            case 6:
                return String.class;
            case 7:
                return String.class;
            default:
                return String.class;
        }
    }

    @Override
    public int getRowCount() {
        return personTableModelData == null ? 0 : personTableModelData.length;
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PersonMessage person = personTableModelData[rowIndex];

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
            default:
                return null;
        }
    }
}