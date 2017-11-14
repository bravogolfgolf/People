package ui_swing;

interface PersonTablePanelListener {
    void rowSelected(int id, String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender);
}
