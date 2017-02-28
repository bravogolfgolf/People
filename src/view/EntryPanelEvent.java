package view;

import java.util.EventObject;

class EntryPanelEvent extends EventObject {
    final String fullName;
    final String occupation;
    final int ageCategory;
    final int employmentStatus;
    final Boolean uSCitizen;
    final String taxId;
    final String gender;

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
}
