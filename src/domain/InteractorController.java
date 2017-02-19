package domain;

import java.io.File;
import java.io.IOException;

public interface InteractorController {
    void addPerson(AddPersonRequest request);

    void exportRepository(File file) throws IOException;

    void loadRepository(File file) throws IOException, ClassNotFoundException;

    void deletePerson(int id);
}
