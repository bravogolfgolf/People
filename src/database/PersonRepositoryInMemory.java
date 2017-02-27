package database;

import databasegateway.PersonRepository;
import entity.PersonTemplate;

import java.util.*;

public class PersonRepositoryInMemory extends PersonRepository {

    private final Map<Integer, Person> people = new HashMap<>();
    private Person person;

    @Override
    public List<PersonTemplate> findAll() {
        List<PersonTemplate> personTemplates = new ArrayList<>();
        for (Person person : people.values())
            personTemplates.add(person);
        return personTemplates;
    }

    @Override
    public void deletePerson(int id) {
        people.remove(id);
    }

    @Override
    public void updatePerson(int id, String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender) {
        person = createPersonTemplate(fullName, occupation, ageCategory, employmentStatus, uSCitizen, taxId, gender, id);
        addPerson(person);
    }

    @Override
    public void addPerson(String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender) {
        int id1;
        try {
            id1 = Collections.max(people.keySet()) + 1;
        } catch (NoSuchElementException e) {
            id1 = 1;
        }
        int id = id1;
        person = createPersonTemplate(fullName, occupation, ageCategory, employmentStatus, uSCitizen, taxId, gender, id);
        addPerson(person);
    }

    private Person createPersonTemplate(String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender, int id) {
        return new Person(id, fullName, occupation, ageCategory, employmentStatus, uSCitizen, taxId, gender);
    }

    private void addPerson(Person person) {
        people.put(person.getId(), person);
    }

    @Override
    public void fromImport(List<Person> people) {
        this.people.clear();
        for (Person person : people) {
            this.people.put(person.getId(), person);
        }
    }

    @Override
    public Collection<Person> forExport() {
        return people.values();
    }
}
