package data;

import entity.PersonTemplate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonRepositoryMySQLTest {

    private final PersonRepository repository = new PersonRepositoryMySQL();
    private final PersonTemplate person = new Person(1, "Full Name", "Occupation", 0, 0, false,
            "Tax ID", "Male");
    private final PersonTemplate person1 = new Person(1, "Update Name", "Update", 1, 1, true,
            "Update ID", "Female");
    private final PersonTemplate person2 = new Person(2, "Full Name", "Occupation", 0, 2, true,
            "Tax ID", "Female");

    private Map<Integer, PersonTemplate> people = new HashMap<>();

    @Before
    public void setUp() {
        clearRepository();
    }

    @After
    public void tearDown() {
        clearRepository();
    }

    private void clearRepository() {
        String url = "jdbc:mysql://localhost:3306/people?useSSL=true";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "briangibson", "sKzuP3RMF");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PreparedStatement statement = null;
        try {
            statement = connection != null ? connection.prepareStatement("delete from person") : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (statement != null) statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
