package domain.exportfile;

import java.io.File;
import java.io.IOException;

public interface Export {
    void toDisk(File file) throws IOException;
}
