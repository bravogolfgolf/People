package usecase;

import builderfactory.Request;
import builderfactory.UseCase;
import gateway.ExportImport;
import responder.ImportResponder;
import responder.ImportResponse;

import java.io.File;
import java.io.IOException;

public class ImportUseCase extends UseCase {
    private final ExportImport importer;
    private final ImportResponder responder;

    public ImportUseCase(ExportImport importer, ImportResponder responder) {
        this.importer = importer;
        this.responder = responder;
    }

    @Override
    public void execute(Request request) {
        ImportRequest r = (ImportRequest) request;
        int count = tryImportFile(r.file);
        ImportResponse response = new ImportUseCaseResponse();
        response.setCount(count);
        responder.present(response);
    }

    private int tryImportFile(File file) {
        int count;
        try {
            count = importer.fromDisk(file);
        } catch (IOException | ClassNotFoundException e) {
            throw new ImportFailed(e);
        }
        return count;
    }

    class ImportFailed extends RuntimeException {
        ImportFailed(Exception e) {
            super(e);
        }
    }
}
