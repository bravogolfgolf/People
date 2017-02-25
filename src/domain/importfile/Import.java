package domain.importfile;

import java.io.File;
import java.io.IOException;

public interface Import {
    void fromDisk(File file) throws IOException, ClassNotFoundException;
}
