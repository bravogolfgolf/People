package data;

import domain.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DatabaseTest {

    private Database db;
    private Person person;
    private Map<Integer,Person> people;

    @Before
    public void setUp() throws Exception {
        db = new Database();
        person = new Person("Full Name", "Occupation", 0, 2, true, "Tax ID",
                "Female");
        people = new HashMap<>();
    }

    @Test
    public void newDatabaseShouldBeEmpty() {
        people = db.getPeople();
        assertEquals(0, people.size());
    }

    @Test
    public void newDatabaseShouldExceptPerson() {
        db.setPerson(person);
        people = db.getPeople();
        assertEquals(1, people.size());
    }

    @Test
    public void shouldNotBeAbleToAddPeopleWithSameID() {
        db.setPerson(person);
        db.setPerson(person);
        people = db.getPeople();
        assertEquals(1, people.size());
    }

    @Test
    public void getAgeCategoriesShouldReturnProperlySortedMap() {
        Map<Integer, String> actual = db.getAgeCategories();
        assertEquals(3, actual.size());
        assertTrue("Under 18".equals(actual.get(0)));
        assertTrue("18 to 65".equals(actual.get(1)));
        assertTrue("Over 65".equals(actual.get(2)));
    }

    @Test
    public void getEmploymentStatusesShouldReturnProperlySortedMap() {
        Map<Integer, String> actual = db.getEmploymentStatuses();
        assertEquals(3, actual.size());
        assertTrue("Employed".equals(actual.get(0)));
        assertTrue("Self-employed".equals(actual.get(1)));
        assertTrue("Unemployed".equals(actual.get(2)));
    }
}
