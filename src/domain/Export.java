package domain;

import java.io.File;
import java.io.IOException;
import java.util.Map;

interface Export {
    void toDisk(Map<Integer, Person> map, File file) throws IOException;
}
