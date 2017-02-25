package data;

import entity.PersonTemplate;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PersonRepositoryMySQL extends PersonRepository {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    void addPerson(PersonTemplate person) {
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
        trySettingDriver();
        String url = "jdbc:mysql://localhost:3306/people?useSSL=true";
        tryGetConnection(url);
    }

    private void trySettingDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new DriverNotFound(e);
        }
    }

    private void tryGetConnection(String url) {
        try {
            connection = DriverManager.getConnection(url, "briangibson", "sKzuP3RMF");
        } catch (SQLException e) {
            throw new UnableToGetConnection(e);
        }
    }

    private void tryPrepareStatement(String sql) {
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new UnableToPrepareStatement(e);
        }
    }

    private void trySetInt(int parameterIndex, int anInt) {
        try {
            preparedStatement.setInt(parameterIndex, anInt);
        } catch (SQLException e) {
            throw new UnableToSetInt(e);
        }
    }

    private void trySetString(int parameterIndex, String aString) {
        try {
            preparedStatement.setString(parameterIndex, aString);
        } catch (SQLException e) {
            throw new UnableToSetString(e);
        }
    }

    private void trySetBoolean(int parameterIndex, Boolean aBoolean) {
        try {
            preparedStatement.setBoolean(parameterIndex, aBoolean);
        } catch (SQLException e) {
            throw new UnableToSetBoolean(e);
        }
    }

    private void tryExecuteUpdate() {
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new UnableToExecuteUpdate(e);
        }
    }

    @Override
    public Map<Integer, PersonTemplate> getPeople() {
        HashMap<Integer, PersonTemplate> people = new HashMap<>();

        connect();
        String sql = "Select id, fullName, occupation, ageCategory, employmentStatus, uSCitizen, taxId, gender from person";
        tryPrepareStatement(sql);
        tryExecuteQuery();
        while (tryIsNext()) {
            PersonTemplate person = new Person(tryGetInt(1), tryGetString(2), tryGetString(3), tryGetInt(4), tryGetInt(5), tryGetBoolean(6), tryGetString(7), tryGetString(8));
            people.put(person.getId(), person);
        }
        return people;
    }

    private void tryExecuteQuery() {
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new UnableToExecuteQuery(e);
        }
    }

    private boolean tryIsNext() {
        boolean aBoolean;
        try {
            aBoolean = resultSet.next();
        } catch (SQLException e) {
            throw new UnableToGetNext(e);
        }
        return aBoolean;
    }

    private int tryGetInt(int columnIndex) {
        int anInt;
        try {
            anInt = resultSet.getInt(columnIndex);
        } catch (SQLException e) {
            throw new UnableToGetInt(e);
        }
        return anInt;
    }

    private boolean tryGetBoolean(int columnIndex) {
        boolean aBoolean;
        try {
            aBoolean = resultSet.getBoolean(columnIndex);
        } catch (SQLException e) {
            throw new UnableToGetBoolean(e);
        }
        return aBoolean;
    }

    private String tryGetString(int columnIndex) {
        String string;
        try {
            string = resultSet.getString(columnIndex);
        } catch (SQLException e) {
            throw new UnableToGetString(e);
        }
        return string;
    }

    @Override
    public void setPeople(Map<Integer, PersonTemplate> people) {
        connect();
        String sql = "delete from person";
        tryPrepareStatement(sql);
        tryExecuteUpdate();
        for (PersonTemplate person : people.values()) {
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

    @Override
    public void updatePerson(PersonTemplate person) {
        connect();
        String sql = "update person set fullName=?, occupation=?, ageCategory=?, employmentStatus=?, uSCitizen=?, taxId=?, gender=? where id=?";
        tryPrepareStatement(sql);
        trySetString(1, person.getFullName());
        trySetString(2, person.getOccupation());
        trySetInt(3, person.getAgeCategory());
        trySetInt(4, person.getEmploymentStatus());
        trySetBoolean(5, person.isUsCitizen());
        trySetString(6, person.getTaxId());
        trySetString(7, person.getGender());
        trySetInt(8, person.getId());
        tryExecuteUpdate();
    }

    private class DriverNotFound extends RuntimeException {
        DriverNotFound(ClassNotFoundException e) {
            super(e);
        }
    }

    private class UnableToGetConnection extends RuntimeException {
        UnableToGetConnection(SQLException e) {
            super(e);
        }
    }

    private class UnableToPrepareStatement extends RuntimeException {
        UnableToPrepareStatement(SQLException e) {
            super(e);
        }
    }

    private class UnableToSetInt extends RuntimeException {
        UnableToSetInt(SQLException e) {
            super(e);
        }
    }

    private class UnableToSetString extends RuntimeException {
        UnableToSetString(SQLException e) {
            super(e);
        }
    }

    private class UnableToSetBoolean extends RuntimeException {
        UnableToSetBoolean(SQLException e) {
            super(e);
        }
    }

    private class UnableToExecuteUpdate extends RuntimeException {
        UnableToExecuteUpdate(SQLException e) {
            super(e);
        }
    }

    private class UnableToExecuteQuery extends RuntimeException {
        UnableToExecuteQuery(SQLException e) {
            super(e);
        }
    }

    private class UnableToGetNext extends RuntimeException {
        UnableToGetNext(SQLException e) {
            super(e);
        }
    }

    private class UnableToGetInt extends RuntimeException {
        UnableToGetInt(SQLException e) {
            super(e);
        }
    }

    private class UnableToGetBoolean extends RuntimeException {
        UnableToGetBoolean(SQLException e) {
            super(e);
        }
    }

    private class UnableToGetString extends RuntimeException {
        UnableToGetString(SQLException e) {
            super(e);
        }
    }
}
