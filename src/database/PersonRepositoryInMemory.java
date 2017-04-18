package database;

import gateway.PersonRepository;

import java.util.*;

public class PersonRepositoryInMemory implements PersonRepository {

    private final Map<Integer, Person> people = new HashMap<>();
    private Person person;

    @Override
    public List findAll() {
        List<Person> people = new ArrayList<>();
        people.addAll(this.people.values());
        return people;
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
    public int addPerson(String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender) {
        int id;
        try {
            id = Collections.max(people.keySet()) + 1;
        } catch (NoSuchElementException e) {
            id = 1;
        }
        person = createPersonTemplate(fullName, occupation, ageCategory, employmentStatus, uSCitizen, taxId, gender, id);
        addPerson(person);
        return id;
    }

    private Person createPersonTemplate(String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender, int id) {
        return new Person(id, fullName, occupation, ageCategory, employmentStatus, uSCitizen, taxId, gender);
    }

    private void addPerson(Person person) {
        people.put(person.getId(), person);
    }

    @Override
    public void fromImport(List people) {
        this.people.clear();
        for (Object object : people) {
            this.people.put(((Person) object).getId(), (Person) object);
        }
    }

    @Override
    public Collection<Person> forExport() {
        return people.values();
    }
}
