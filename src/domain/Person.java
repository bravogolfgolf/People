package domain;

public class Person {

    private static int count;
    private final int id;
    private final String fullName;
    private final String occupation;
    private final int ageCategory;
    private final int employmentStatus;
    private final boolean uSCitizen;
    private final String taxId;
    private final String gender;

    public Person(String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender) {
        this.id = count++;
        this.fullName = fullName;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.employmentStatus = employmentStatus;
        this.uSCitizen = uSCitizen;
        this.taxId = taxId;
        this.gender = gender;
    }

    public int getId() {
        return id;
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

    public boolean isUsCitizen() {
        return uSCitizen;
    }

    public String getTaxId() {
        return taxId;
    }

    public String getGender() {
        return gender;
    }
}