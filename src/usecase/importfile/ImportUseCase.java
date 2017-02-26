package usecase.importfile;

import exportimportgateway.Import;
import requestor.Request;
import requestor.UseCase;
import responder.Presenter;

import java.io.File;
import java.io.IOException;

public class ImportUseCase extends UseCase {
    private final Import importer;

    public ImportUseCase(Import importer, Presenter presenter) {
        this.importer = importer;
    }

    public void execute(Request request) {
        ImportRequest r = (ImportRequest) request;
        tryImportFile(r.file);
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
