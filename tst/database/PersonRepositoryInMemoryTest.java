package database;

import entity.PersonTemplate;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonRepositoryInMemoryTest {

    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final PersonTemplate person = new Person(1, "Full Name", "Occupation", 0, 0, false,
            "Tax ID", "Male");
    private final PersonTemplate person1 = new Person(1, "Update Name", "Update", 1, 1, true,
            "Update ID", "Female");
    private final PersonTemplate person2 = new Person(2, "Updated Name", "Occupation", 0, 2, true,
            "Tax ID", "Female");
    private Map<Integer, PersonTemplate> people = new HashMap<>();

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
    public void shouldBeAbleToUpdatePeopleWithSameID() {
        repository.addPerson(person);
        repository.updatePerson(person1);
        people = repository.getPeople();
        assertEquals(1, people.size());
        for (PersonTemplate person : people.values()) {
            assertEquals(person.getId(), person1.getId());
            assertEquals(person.getFullName(), person1.getFullName());
            assertEquals(person.getOccupation(), person1.getOccupation());
            assertEquals(person.getAgeCategory(), person1.getAgeCategory());
            assertEquals(person.getEmploymentStatus(), person1.getEmploymentStatus());
            assertTrue(person.isUsCitizen());
            assertEquals(person.getTaxId(), person1.getTaxId());
            assertEquals(person.getGender(), person1.getGender());
        }
    }

    @Test
    public void shouldBeAbleToDeletePerson() {
        int id = 2;
        repository.addPerson(person);
        repository.addPerson(person2);
        assertEquals(2, repository.getPeople().size());
        repository.deletePerson(id);
        assertEquals(1, repository.getPeople().size());
    }

    @Test
    public void shouldBeAbleToReplaceRepository() {
        repository.addPerson(person);
        Map<Integer, PersonTemplate> expected = new HashMap<>(repository.getPeople());
        assertEquals(1, expected.size());

        repository.addPerson(person2);
        Map<Integer, PersonTemplate> updated = repository.getPeople();
        assertEquals(2, updated.size());

        repository.setPeople(expected);
        Map<Integer, PersonTemplate> actual = repository.getPeople();
        assertEquals(1, actual.size());
    }
}
