package database;

import databasegateway.PersonRepository;
import entity.PersonTemplate;

import java.util.HashMap;
import java.util.Map;

public class PersonRepositoryInMemory extends PersonRepository {

    private Map<Integer, PersonTemplate> people = new HashMap<>();
    private PersonTemplate personTemplate;

    @Override
    public Map<Integer, PersonTemplate> getPeople() {
        return people;
    }

    @Override
    public void setPeople(Map<Integer, PersonTemplate> people) {
        this.people = people;
    }

    @Override
    public void deletePerson(int id) {
        people.remove(id);
    }

    @Override
    public void updatePerson(int id, String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender) {
        personTemplate = createPersonTemplate(fullName, occupation, ageCategory, employmentStatus, uSCitizen, taxId, gender, id);
        addPerson(personTemplate);
    }

    @Override
    public void addPerson(String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender) {
        int id = determineId(getPeople().keySet());
        personTemplate = createPersonTemplate(fullName, occupation, ageCategory, employmentStatus, uSCitizen, taxId, gender, id);
        addPerson(personTemplate);
    }

    private PersonTemplate createPersonTemplate(String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender, int id) {
        return new Person(id, fullName, occupation, ageCategory, employmentStatus, uSCitizen, taxId, gender);
    }

    private void addPerson(PersonTemplate person) {
        people.put(person.getId(), person);
    }
}
