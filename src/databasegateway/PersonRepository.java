package databasegateway;

import java.util.Collection;
import java.util.List;

public interface PersonRepository {
    List findAll();

    void deletePerson(int id);

    void updatePerson(int id, String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender);

    void addPerson(String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender);

    void fromImport(List people);

    Collection forExport();
}
