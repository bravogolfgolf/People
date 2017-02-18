package domain;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public interface InteractorController {
    void addPerson(PersonMessage request) throws SQLException, ClassNotFoundException;

    void exportRepository(File file) throws IOException, SQLException, ClassNotFoundException;

    void loadRepository(File file) throws IOException, ClassNotFoundException, SQLException;

    void deletePerson(int id) throws SQLException, ClassNotFoundException;
}
