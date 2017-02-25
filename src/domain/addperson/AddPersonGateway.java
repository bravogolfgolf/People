package domain.addperson;

public interface AddPersonGateway {
    void addPerson(String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender);
}
