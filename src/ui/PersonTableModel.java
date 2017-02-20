package ui;

import domain.RefreshResponse;

import javax.swing.table.AbstractTableModel;

class PersonTableModel extends AbstractTableModel {

    private RefreshResponse[] personTableModelData;
    private final String[] columnNames = {"ID", "Full Name", "Occupation", "Age Category",
            "Employment Status", "US Citizen", "Tax ID", "Gender"};

    void addDataForPersonTableModel(RefreshResponse[] responses) {
        personTableModelData = responses;
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
        RefreshResponse response = personTableModelData[rowIndex];

        switch (columnIndex) {
            case 0:
                return response.id;
            case 1:
                return response.fullName;
            case 2:
                return response.occupation;
            case 3:
                return response.ageCategory;
            case 4:
                return response.employmentStatus;
            case 5:
                return response.uSCitizen;
            case 6:
                return response.taxId;
            case 7:
                return response.gender;
            default:
                return null;
        }
    }
}