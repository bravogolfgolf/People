package data;

import domain.Person;
import domain.RepositoryInteractor;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PersonRepositoryMySQL implements RepositoryInteractor {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public void addPerson(Person person) {
        connect();
        String sql = "insert into person set id=?,  fullName=?,  occupation=?,  ageCategory=?,  employmentStatus=?, uSCitizen=?, taxId=?, gender=?";
        tryPrepareStatement(sql);
        trySetInt(1, person.getId());
        trySetString(2, person.getFullName());
        trySetString(3, person.getOccupation());
        trySetInt(4, person.getAgeCategory());
        trySetInt(5, person.getEmploymentStatus());
        trySetBoolean(6, person.isUsCitizen());
        trySetString(7, person.getTaxId());
        trySetString(8, person.getGender());
        tryExecuteUpdate();
    }

    private void connect() {
        trySettingDrive();
        String url = "jdbc:mysql://localhost:3306/people?useSSL=true";
        tryGetConnection(url);
    }

    private void trySettingDrive() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void tryGetConnection(String url) {
        try {
            connection = DriverManager.getConnection(url, "briangibson", "sKzuP3RMF");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void tryPrepareStatement(String sql) {
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void trySetInt(int parameterIndex, int anInt) {
        try {
            preparedStatement.setInt(parameterIndex, anInt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void trySetString(int parameterIndex, String aString) {
        try {
            preparedStatement.setString(parameterIndex, aString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void trySetBoolean(int parameterIndex, Boolean aBoolean) {
        try {
            preparedStatement.setBoolean(parameterIndex, aBoolean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void tryExecuteUpdate() {
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Person> getPeople() {
        HashMap<Integer, Person> people = new HashMap<>();

        connect();
        String sql = "Select id, fullName, occupation, ageCategory, employmentStatus, uSCitizen, taxId, gender from person";
        tryPrepareStatement(sql);
        tryExecuteQuery();
        while (tryIsNext()) {
            Person person = new Person(tryGetInt(1), tryGetString(2), tryGetString(3), tryGetInt(4), tryGetInt(5), tryGetBoolean(6), tryGetString(7), tryGetString(8));
            people.put(person.getId(), person);
        }
        return people;
    }

    private void tryExecuteQuery() {
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean tryIsNext() {
        boolean aBoolean = false;
        try {
            aBoolean = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aBoolean;
    }

    private int tryGetInt(int columnIndex) {
        int anInt = 0;
        try {
            anInt = resultSet.getInt(columnIndex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return anInt;
    }

    private boolean tryGetBoolean(int columnIndex) {
        boolean aBoolean = false;
        try {
            aBoolean = resultSet.getBoolean(columnIndex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aBoolean;
    }

    private String tryGetString(int columnIndex) {
        String string = null;
        try {
            string = resultSet.getString(columnIndex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return string;
    }

    @Override
    public void setPeople(Map<Integer, Person> people) {
        connect();
        String sql = "delete from person";
        tryPrepareStatement(sql);
        tryExecuteUpdate();
        for (Person person : people.values()) {
            addPerson(person);
        }
    }

    @Override
    public void deletePerson(int id) {
        connect();
        String sql = "delete from person where id=?";
        tryPrepareStatement(sql);
        trySetInt(1, id);
        tryExecuteUpdate();
    }
}
