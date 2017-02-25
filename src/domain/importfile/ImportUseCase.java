package domain.importfile;

import domain.InputBoundary;
import domain.Request;

import java.io.File;
import java.io.IOException;

public class ImportUseCase implements InputBoundary {
    private final Import importer;
    private final InputBoundary refreshUseCase;

    public ImportUseCase(Import importer, InputBoundary refreshUseCase) {
        this.importer = importer;
        this.refreshUseCase = refreshUseCase;
    }

    @Override
    public void execute(Request request) {
        ImportRequest r = (ImportRequest) request;
        tryImportFile(r.file);
        refreshUseCase.execute(request);
    }

    private void tryImportFile(File file) {
        try {
            importer.fromDisk(file);
        } catch (IOException | ClassNotFoundException e) {
            throw new ImportFailed(e);
        }
    }

    public class ImportFailed extends RuntimeException {
        ImportFailed(Exception e) {
            super(e);
        }
    }
}
