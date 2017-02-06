package domain;

import java.io.File;
import java.io.IOException;

public interface InteractorController {
    void addPerson(PersonMessage request);

    void exportRepository(File file) throws IOException;

    void loadRepository(File file) throws IOException;

    void deletePerson(int id);
}
