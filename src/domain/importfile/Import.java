package domain.importfile;

import domain.Person;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface Import {
    Map<Integer, Person> fromDisk(File file) throws IOException, ClassNotFoundException;
}
