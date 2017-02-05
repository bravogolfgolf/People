package domain;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonRepositoryTest {

    private PersonRepository repository;
    private Person person;
    private Map<Integer, Person> people;

    @Before
    public void setUp() throws Exception {
        Person.setCounter(0);
        repository = new PersonRepository();
        person = new Person("Full Name", "Occupation", 0, 2, true, "Tax ID",
                "Female");
        people = new HashMap<>();
    }

    @Test
    public void newDatabaseShouldBeEmpty() {
        people = repository.getPeople();
        assertEquals(0, people.size());
    }

    @Test
    public void newDatabaseShouldExceptPerson() {
        repository.addPerson(person);
        people = repository.getPeople();
        assertEquals(1, people.size());
    }

    @Test
    public void shouldNotBeAbleToAddPeopleWithSameID() {
        repository.addPerson(person);
        repository.addPerson(person);
        people = repository.getPeople();
        assertEquals(1, people.size());
    }

    @Test
    public void shouldBeAbleToReplaceRepository() {
        repository.addPerson(person);
        Map<Integer, Person> expected = new HashMap<>(repository.getPeople());
        assertEquals(1, expected.size());

        person = new Person("Full Name", "Occupation", 0, 2, true, "Tax ID",
                "Female");
        repository.addPerson(person);
        Map<Integer, Person> updated = repository.getPeople();
        assertEquals(2, updated.size());

        repository.setPeople(expected);
        Map<Integer, Person> actual = repository.getPeople();
        assertEquals(1, actual.size());

        assertTrue(!expected.equals(updated));
        assertTrue(!updated.equals(actual));
        assertTrue(actual.equals(expected));
    }

    @Test
    public void getAgeCategoriesShouldReturnProperlySortedMap() {
        Map<Integer, String> actual = repository.getAgeCategories();
        assertEquals(3, actual.size());
        assertTrue("Under 18".equals(actual.get(0)));
        assertTrue("18 to 65".equals(actual.get(1)));
        assertTrue("Over 65".equals(actual.get(2)));
    }

    @Test
    public void getEmploymentStatusesShouldReturnProperlySortedMap() {
        Map<Integer, String> actual = repository.getEmploymentStatuses();
        assertEquals(3, actual.size());
        assertTrue("Employed".equals(actual.get(0)));
        assertTrue("Self-employed".equals(actual.get(1)));
        assertTrue("Unemployed".equals(actual.get(2)));
    }
}
