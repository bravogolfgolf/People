package data;

import domain.Person;
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

public class PersonRepositoryMySQLTest {

    private PersonRepositoryMySQL repository;
    private Person person;
    private Map<Integer, Person> people;

    @Before
    public void setUp() throws Exception {
        repository = new PersonRepositoryMySQL();
        person = new Person(1, "Full Name", "Occupation", 0, 2, true,
                "Tax ID", "Female");
        people = new HashMap<>();
    }

    @After
    public void tearDown() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/people?useSSL=true";
        Connection connection = DriverManager.getConnection(url, "briangibson", "sKzuP3RMF");
        PreparedStatement statement = connection.prepareStatement("delete from person");
        statement.executeUpdate();
    }

    @Test
    public void newDatabaseShouldBeEmpty() throws SQLException, ClassNotFoundException {
        people = repository.getPeople();
        assertEquals(0, people.size());
    }

    @Test
    public void newDatabaseShouldExceptPerson() throws SQLException, ClassNotFoundException {
        repository.addPerson(person);
        people = repository.getPeople();
        assertEquals(1, people.size());
    }

    @Test
    public void shouldNotBeAbleToAddPeopleWithSameID() throws SQLException, ClassNotFoundException {
        repository.addPerson(person);
        repository.addPerson(person);
        people = repository.getPeople();
        assertEquals(1, people.size());
    }

    @Test
    public void shouldBeAbleToDeletePerson() throws SQLException, ClassNotFoundException {
        int id = 2;
        Person person1 = new Person(id, "Full Name", "Occupation", 0, 2, true,
                "Tax ID", "Female");
        repository.addPerson(person);
        repository.addPerson(person1);
        assertEquals(2, repository.getPeople().size());
        repository.deletePerson(id);
        assertEquals(1, repository.getPeople().size());
    }

    @Test
    public void shouldBeAbleToReplaceRepository() throws SQLException, ClassNotFoundException {
        repository.addPerson(person);
        Map<Integer, Person> expected = new HashMap<>(repository.getPeople());
        assertEquals(1, expected.size());

        person = new Person(2, "Full Name", "Occupation", 0, 2, true,
                "Tax ID", "Female");
        repository.addPerson(person);
        Map<Integer, Person> updated = repository.getPeople();
        assertEquals(2, updated.size());

        repository.setPeople(expected);
        Map<Integer, Person> actual = repository.getPeople();
        assertEquals(1, actual.size());
    }
}
