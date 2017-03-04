package gateway;

import java.io.File;
import java.io.IOException;

public interface ExportImport {
    int toDisk(File file) throws IOException;
    int fromDisk(File file) throws IOException, ClassNotFoundException;
}
