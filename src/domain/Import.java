package domain;

import java.io.File;
import java.io.IOException;
import java.util.Map;

interface Import {
    Map<Integer, Person> fromDisk(File file) throws IOException, ClassNotFoundException;
}
