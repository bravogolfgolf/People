package data;

import domain.addperson.AddPersonGateway;
import domain.deleteperson.DeletePersonGateway;
import domain.refresh.RefreshGateway;
import domain.updateperson.UpdatePersonGateway;
import entity.PersonTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class PersonRepository
        implements RefreshGateway, AddPersonGateway, UpdatePersonGateway, DeletePersonGateway {

    @Override
    public void addPerson(String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender) {
        int id = determineId();
        PersonTemplate person = new Person(id, fullName, occupation, ageCategory, employmentStatus, uSCitizen, taxId, gender);
        addPerson(person);
    }

    private int determineId() {
        int id;
        try {
            id = Collections.max(getPeople().keySet()) + 1;
        } catch (NoSuchElementException e) {
            id = 1;
        }
        return id;
    }

    abstract void addPerson(PersonTemplate person);

    public abstract void setPeople(Map<Integer, PersonTemplate> map);

    public abstract void deletePerson(int id);

    public abstract void updatePerson(PersonTemplate person);

    public abstract Map<Integer, PersonTemplate> getPeople();
}
