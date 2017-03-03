package gateway;

import java.io.File;
import java.io.IOException;

public interface ExportImport {
    void toDisk(File file) throws IOException;
    void fromDisk(File file) throws IOException, ClassNotFoundException;
}
