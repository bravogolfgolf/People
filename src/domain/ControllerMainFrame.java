package domain;

import java.io.File;
import java.io.IOException;

public interface ControllerMainFrame {
    void addPerson(FormEvent formEvent);

    void exportRepository(File file) throws IOException;

    void loadRepository(File file) throws IOException;
}
