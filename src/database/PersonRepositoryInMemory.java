package database;

import entity.PersonTemplate;

import java.util.HashMap;
import java.util.Map;

public class PersonRepositoryInMemory extends PersonRepository {

    private Map<Integer, PersonTemplate> people = new HashMap<>();

    @Override
    public void addPerson(PersonTemplate person) {
        people.put(person.getId(), person);
    }

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
    public void updatePerson(PersonTemplate person) {
        people.put(person.getId(), person);
    }
}