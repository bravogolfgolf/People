package domain;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface PersistentInteractor {
    void export(Map<Integer, Person> map, File file) throws IOException;

    Map<Integer, Person> load(File file) throws IOException;
}
