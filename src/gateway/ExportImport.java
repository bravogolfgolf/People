package gateway;

import java.io.File;
import java.io.IOException;

public interface ExportImport {
    int toDisk(File file) throws IOException;
    void fromDisk(File file) throws IOException, ClassNotFoundException;
}
