package domain.exportfile;

import entity.PersonTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface Export {
    void toDisk(Map<Integer, PersonTemplate> map, File file) throws IOException;
}
