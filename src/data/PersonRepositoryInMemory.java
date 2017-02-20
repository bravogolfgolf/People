package data;

import domain.Person;

import java.util.HashMap;
import java.util.Map;

public class PersonRepositoryInMemory extends RepositoryInteractor {

    private Map<Integer, Person> people = new HashMap<>();

    @Override
    public void addPerson(Person person) {
        people.put(person.getId(), person);
    }

    @Override
    public Map<Integer, Person> getPeople() {
        return people;
    }

    @Override
    public void setPeople(Map<Integer, Person> people) {
        this.people = people;
    }

    @Override
    public void deletePerson(int id) {
        people.remove(id);
    }
}