package entity;

import java.io.Serializable;

public abstract class PersonTemplate implements Serializable {
    private static final long serialVersionUID = 2L;
    protected final int id;
    protected final String fullName;
    protected final String occupation;
    protected final int ageCategory;
    protected final int employmentStatus;
    protected final boolean uSCitizen;
    protected final String taxId;
    protected final String gender;

    protected PersonTemplate(int id, String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender) {
        this.id = id;
        this.fullName = fullName;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.employmentStatus = employmentStatus;
        this.uSCitizen = uSCitizen;
        this.taxId = taxId;
        this.gender = gender;
    }

    public abstract int getId();

    public abstract String getFullName();

    public abstract String getOccupation();

    public abstract int getAgeCategory();

    public abstract int getEmploymentStatus();

    public abstract boolean isUsCitizen();

    public abstract String getTaxId();

    public abstract String getGender();
}
