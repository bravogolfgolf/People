package database;

import gateway.PersonRepository;

import java.sql.*;
import java.util.*;

public class PersonRepositoryMySQL implements PersonRepository {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private void addPerson(int id, String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender) {
        connect();
        String sql = "insert into person set id=?,  fullName=?,  occupation=?,  ageCategory=?,  employmentStatus=?, uSCitizen=?, taxId=?, gender=?";
        tryPrepareStatement(sql);
        trySetInt(1, id);
        trySetString(2, fullName);
        trySetString(3, occupation);
        trySetInt(4, ageCategory);
        trySetInt(5, employmentStatus);
        trySetBoolean(6, uSCitizen);
        trySetString(7, taxId);
        trySetString(8, gender);
        tryExecuteUpdate();

    }

    private void connect() {
        String url = "jdbc:mysql://localhost:3306/people?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        tryGetConnection(url);
    }

    private void tryGetConnection(String url) {
        try {
            connection = DriverManager.getConnection(url, "briangibson", "3g463b279Xq6#2f8");
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
    public List findAll() {
        List<Person> people = new ArrayList<>();

        connect();
        String sql = "Select id, fullName, occupation, ageCategory, employmentStatus, uSCitizen, taxId, gender from person";
        tryPrepareStatement(sql);
        tryExecuteQuery();
        while (tryIsNext()) {
            Person person = new Person(tryGetInt(1), tryGetString(2), tryGetString(3), tryGetInt(4), tryGetInt(5), tryGetBoolean(6), tryGetString(7), tryGetString(8));
            people.add(person);
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
    public void deletePerson(int id) {
        connect();
        String sql = "delete from person where id=?";
        tryPrepareStatement(sql);
        trySetInt(1, id);
        tryExecuteUpdate();
    }

    @Override
    public void updatePerson(int id, String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender) {
        connect();
        String sql = "update person set fullName=?, occupation=?, ageCategory=?, employmentStatus=?, uSCitizen=?, taxId=?, gender=? where id=?";
        tryPrepareStatement(sql);
        trySetString(1, fullName);
        trySetString(2, occupation);
        trySetInt(3, ageCategory);
        trySetInt(4, employmentStatus);
        trySetBoolean(5, uSCitizen);
        trySetString(6, taxId);
        trySetString(7, gender);
        trySetInt(8, id);
        tryExecuteUpdate();
    }

    public int addPerson(String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender) {
        connect();
        String sql = "select max(id) from person";
        tryPrepareStatement(sql);
        tryExecuteQuery();
        int id = 0;
        while (tryIsNext()) {
            id = tryGetInt(1) + 1;
        }
        addPerson(id, fullName, occupation, ageCategory, employmentStatus, uSCitizen, taxId, gender);
        return id;
    }

    @Override
    public void fromImport(List people) {
        connect();
        String sql = "delete from person";
        tryPrepareStatement(sql);
        tryExecuteUpdate();
        for (Object object : people) {
            Person person = (Person) object;
            addPerson(person.getId(), person.getFullName(), person.getOccupation(), person.getAgeCategory(), person.getEmploymentStatus(), person.isUsCitizen(), person.getTaxId(), person.getGender());
        }
    }

    @Override
    public Collection<Person> forExport() {
        Set<Person> people = new HashSet<>();
        connect();
        String sql = "Select id, fullName, occupation, ageCategory, employmentStatus, uSCitizen, taxId, gender from person";
        tryPrepareStatement(sql);
        tryExecuteQuery();
        while (tryIsNext()) {
            Person person = new Person(tryGetInt(1), tryGetString(2), tryGetString(3), tryGetInt(4), tryGetInt(5), tryGetBoolean(6), tryGetString(7), tryGetString(8));
            people.add(person);
        }
        return people;
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
