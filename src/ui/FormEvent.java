package ui;

import domain.FormEventController;

import java.util.EventObject;

public class FormEvent extends EventObject implements FormEventController {
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

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public String getOccupation() {
        return occupation;
    }

    @Override
    public int getAgeCategory() {
        return ageCategory;
    }

    @Override
    public int getEmploymentStatus() {
        return employmentStatus;
    }

    @Override
    public Boolean isUsCitizen() {
        return uSCitizen;
    }

    @Override
    public String getTaxId() {
        return taxId;
    }

    @Override
    public String getGender() {
        return gender;
    }
}
