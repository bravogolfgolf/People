package databasegateway;

public interface UpdatePersonGateway {
    void updatePerson(int id, String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender);
}
