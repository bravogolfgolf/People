package domain;

import domain.RepositoryInteractor;
import domain.Person;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PersonRepository implements RepositoryInteractor {

    private final Map<Integer, Person> people = new HashMap<>();

    private final Map<Integer, String> ageCategories = new TreeMap<Integer, String>() {{
        put(0, "Under 18");
        put(1, "18 to 65");
        put(2, "Over 65");
    }};

    private final Map<Integer, String> employmentStatuses = new TreeMap<Integer, String>() {{
        put(0, "Employed");
        put(1, "Self-employed");
        put(2, "Unemployed");
    }};

    @Override
    public void addPerson(Person person) {
        people.put(person.getId(), person);
    }

    @Override
    public Map<Integer, Person> getPeople() {
        return people;
    }

    Map<Integer, String> getEmploymentStatuses() {
        return Collections.unmodifiableMap(employmentStatuses);
    }

    Map<Integer, String> getAgeCategories() {
        return Collections.unmodifiableMap(ageCategories);
    }
}