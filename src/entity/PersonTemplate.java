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

    protected PersonTemplate(int id, int ageCategory, String gender, boolean uSCitizen, String taxId, String fullName, int employmentStatus, String occupation) {
        this.id = id;
        this.ageCategory = ageCategory;
        this.gender = gender;
        this.uSCitizen = uSCitizen;
        this.taxId = taxId;
        this.fullName = fullName;
        this.employmentStatus = employmentStatus;
        this.occupation = occupation;
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
