package ui_swing;

import java.util.EventObject;

class EntryPanelUpdateEvent extends EventObject {
    final int id;
    final String fullName;
    final String occupation;
    final int ageCategory;
    final int employmentStatus;
    final Boolean uSCitizen;
    final String taxId;
    final String gender;

    EntryPanelUpdateEvent(Object source, int id, String fullName, String occupation, int ageCategory, int employmentStatus, Boolean uSCitizen, String taxId, String gender) {
        super(source);
        this.id=id;
        this.fullName = fullName;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.employmentStatus = employmentStatus;
        this.uSCitizen = uSCitizen;
        this.taxId = taxId;
        this.gender = gender;
    }
}
