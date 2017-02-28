package usecase.importfile;

import exportimportgateway.ExportImport;
import requestor.Request;
import requestor.UseCase;
import responder.Presenter;

import java.io.File;
import java.io.IOException;

public class ImportUseCase extends UseCase {
    private final ExportImport importer;

    public ImportUseCase(ExportImport importer, Presenter presenter) {
        this.importer = importer;
    }

    @Override
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
