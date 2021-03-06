package database;

import entity.PersonTemplate;
import gateway.PersonRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonRepositoryMySQLTest {

    private final PersonRepository repository = new PersonRepositoryMySQL();

    @Before
    public void setUp() {
        clearRepository();
    }

    @After
    public void tearDown() {
        clearRepository();
    }

    private void clearRepository() {
        String url = "jdbc:mysql://localhost:3306/people?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "briangibson", "3g463b279Xq6#2f8");
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
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void newDatabaseShouldExceptPerson() {
        int id = repository.addPerson("Full Name", "Occupation", 0, 0, false,
                "Tax ID", "Male");
        assertEquals(1, id);
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void shouldBeAbleToUpdatePeopleWithSameID() {
        repository.addPerson("Full Name", "Occupation", 0, 0, false,
                "Tax ID", "Male");
        repository.updatePerson(1, "Update Name", "Update", 1, 1, true,
                "Update ID", "Female");
        assertEquals(1, repository.findAll().size());
        for (Object object : repository.findAll()) {
            PersonTemplate person = (PersonTemplate) object;
            assertEquals(1, person.getId());
            assertEquals("Update Name", person.getFullName());
            assertEquals("Update", person.getOccupation());
            assertEquals(1, person.getAgeCategory());
            assertEquals(1, person.getEmploymentStatus());
            assertTrue(person.isUsCitizen());
            assertEquals("Update ID", person.getTaxId());
            assertEquals("Female", person.getGender());
        }
    }

    @Test
    public void shouldBeAbleToDeletePerson() {
        int id = 2;
        repository.addPerson("Full Name", "Occupation", 0, 0, false,
                "Tax ID", "Male");
        repository.addPerson("Full Name", "Occupation", 0, 2, true,
                "Tax ID", "Female");
        assertEquals(2, repository.findAll().size());
        repository.deletePerson(id);
        assertEquals(1, repository.findAll().size());
    }
}
