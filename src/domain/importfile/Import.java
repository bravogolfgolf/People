package domain.importfile;

import entity.PersonTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface Import {
    Map<Integer, PersonTemplate> fromDisk(File file) throws IOException, ClassNotFoundException;
}
