package database;

public class Person extends entity.PersonTemplate {

    public Person(int id, String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender) {
        super(id, fullName, occupation, ageCategory, employmentStatus, uSCitizen, taxId, gender);
    }

    @Override
    public int getId() {
        return id;
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
    public boolean isUsCitizen() {
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