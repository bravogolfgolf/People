package domain;

import java.io.File;
import java.io.IOException;

public class ExportUseCase implements UseCase {
    private final RepositoryInteractor repository;
    private final PersistentInteractor exportImport;

    ExportUseCase(RepositoryInteractor repository, PersistentInteractor exportImport) {
        this.repository = repository;
        this.exportImport = exportImport;
    }

    @Override
    public void execute(Request request) {
        ExportRequest r = (ExportRequest) request;
        File file = new File(r.file);
        tryExport(file);
    }

    private void tryExport(File file) {
        try {
            exportImport.export(repository.getPeople(), file);
        } catch (IOException e) {
            throw new ExportFailed(e);
        }
    }

    class ExportFailed extends RuntimeException {
        ExportFailed(IOException e) {
            super(e);
        }
    }
}
