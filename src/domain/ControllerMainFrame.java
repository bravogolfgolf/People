package domain;

import java.io.File;
import java.io.IOException;

public interface ControllerMainFrame {
    void addPerson(FormEventController formEvent);

    void exportRepository(File file) throws IOException;

    void loadRepository(File file) throws IOException;

    void deletePerson(int id);
}
