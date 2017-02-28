package view;

import java.util.EventObject;

class EntryPanelEvent extends EventObject {
    private final String fullName;
    private final String occupation;
    private final int ageCategory;
    private final int employmentStatus;
    private final Boolean uSCitizen;
    private final String taxId;
    private final String gender;

    EntryPanelEvent(Object source, String fullName, String occupation, int ageCategory, int employmentStatus, Boolean uSCitizen, String taxId, String gender) {
        super(source);
        this.fullName = fullName;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.employmentStatus = employmentStatus;
        this.uSCitizen = uSCitizen;
        this.taxId = taxId;
        this.gender = gender;
    }

    Object[] toObjects() {
        Object[] objects = new Object[7];
        objects[0] = fullName;
        objects[1] = occupation;
        objects[2] = ageCategory;
        objects[3] = employmentStatus;
        objects[4] = uSCitizen;
        objects[5] = taxId;
        objects[6] = gender;
        return objects;
    }
}
