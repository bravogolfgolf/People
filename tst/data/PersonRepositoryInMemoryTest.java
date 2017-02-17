package data;

import domain.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonRepositoryInMemoryTest {

    private PersonRepositoryInMemory repository;
    private Person person;
    private Map<Integer, Person> people;

    @Before
    public void setUp() throws Exception {
        Person.setCounter(1);
        repository = new PersonRepositoryInMemory();
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
    public void shouldBeAbleToDeletePerson() {
        int id = 1;
        Person person1 = new Person("Full Name", "Occupation", 0, 2, true, "Tax ID",
                "Female");
        repository.addPerson(person);
        repository.addPerson(person1);
        assertEquals(2, repository.getPeople().size());
        repository.deletePerson(id);
        assertEquals(1, repository.getPeople().size());
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
}
