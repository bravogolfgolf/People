package data;

import domain.Person;
import domain.RepositoryInteractor;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PersonRepositoryMySQL implements RepositoryInteractor {
    private Connection connection;
    private HashMap<Integer, Person> people = new HashMap<>();

    public void addPerson(Person person) throws SQLException, ClassNotFoundException {
        connect();
        String sql = "insert into person set id=?,  fullName=?,  occupation=?,  ageCategory=?,  employmentStatus=?, uSCitizen=?, taxId=?, gender=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, person.getId());
        statement.setString(2, person.getFullName());
        statement.setString(3, person.getOccupation());
        statement.setInt(4, person.getAgeCategory());
        statement.setInt(5, person.getEmploymentStatus());
        statement.setBoolean(6, person.isUsCitizen());
        statement.setString(7, person.getTaxId());
        statement.setString(8, person.getGender());
        statement.executeUpdate();
    }

    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/people?useSSL=true";
        connection = DriverManager.getConnection(url, "briangibson", "sKzuP3RMF");
    }

    public Map<Integer, Person> getPeople() throws ClassNotFoundException, SQLException {
        connect();
        String sql = "Select id, fullName, occupation, ageCategory, employmentStatus, uSCitizen, taxId, gender from person";
        Statement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery(sql);
        people = new HashMap<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String fullName = resultSet.getString(2);
            String occupation = resultSet.getString(3);
            int ageCategory = resultSet.getInt(4);
            int employmentStatus = resultSet.getInt(5);
            boolean uSCitizen = resultSet.getBoolean(6);
            String taxId = resultSet.getString(7);
            String gender = resultSet.getString(8);
            Person person = new Person(id, fullName, occupation, ageCategory, employmentStatus, uSCitizen, taxId, gender);
            people.put(person.getId(), person);
        }
        return people;
    }

    @Override
    public void setPeople(Map<Integer, Person> people) throws SQLException, ClassNotFoundException {
        connect();
        String sql = "delete from person";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        for (Person person : people.values()) {
            addPerson(person);
        }
    }

    @Override
    public void deletePerson(int id) throws SQLException, ClassNotFoundException {
        connect();
        String sql = "delete from person where id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
