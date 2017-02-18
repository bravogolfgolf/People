package domain;

import java.sql.SQLException;
import java.util.Map;

public interface RepositoryInteractor {
    void addPerson(Person person) throws SQLException, ClassNotFoundException;

    Map<Integer, Person> getPeople() throws ClassNotFoundException, SQLException;

    void setPeople(Map<Integer, Person> people) throws SQLException, ClassNotFoundException;

    void deletePerson(int id) throws SQLException, ClassNotFoundException;
}
