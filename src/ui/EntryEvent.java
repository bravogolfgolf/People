package ui;

import java.util.EventObject;

class EntryEvent extends EventObject {
    private final String fullName;
    private final String occupation;
    private final int ageCategory;
    private final int employmentStatus;
    private final Boolean uSCitizen;
    private final String taxId;
    private final String gender;

    EntryEvent(Object source, String fullName, String occupation, int ageCategory, int employmentStatus, Boolean uSCitizen, String taxId, String gender) {
        super(source);
        this.fullName = fullName;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.employmentStatus = employmentStatus;
        this.uSCitizen = uSCitizen;
        this.taxId = taxId;
        this.gender = gender;
    }

    String getFullName() {
        return fullName;
    }

    String getOccupation() {
        return occupation;
    }

    int getAgeCategory() {
        return ageCategory;
    }

    int getEmploymentStatus() {
        return employmentStatus;
    }

    Boolean isUsCitizen() {
        return uSCitizen;
    }

    String getTaxId() {
        return taxId;
    }

    String getGender() {
        return gender;
    }
}
