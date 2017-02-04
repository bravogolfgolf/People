package domain;

import java.util.EventObject;

public class FormEvent extends EventObject {
    private final String fullName;
    private final String occupation;
    private final int ageCategory;
    private final int employmentStatus;
    private final Boolean uSCitizen;
    private final String taxId;
    private final String gender;

    public FormEvent(Object source, String fullName, String occupation, int ageCategory, int employmentStatus, Boolean uSCitizen, String taxId, String gender) {
        super(source);
        this.fullName = fullName;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.employmentStatus = employmentStatus;
        this.uSCitizen = uSCitizen;
        this.taxId = taxId;
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public String getOccupation() {
        return occupation;
    }

    public int getAgeCategory() {
        return ageCategory;
    }

    public int getEmploymentStatus() {
        return employmentStatus;
    }

    Boolean isUsCitizen() {
        return uSCitizen;
    }

    public String getTaxId() {
        return taxId;
    }

    public String getGender() {
        return gender;
    }
}
