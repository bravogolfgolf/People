package domain;

import ui.Request;
import ui.UseCase;

import java.io.File;
import java.io.IOException;

public class ExportUseCase implements UseCase {
    private final RepositoryInteractor repository;
    private final Export exporter;

    public ExportUseCase(RepositoryInteractor repository, Export exporter) {
        this.repository = repository;
        this.exporter = exporter;
    }

    @Override
    public void execute(Request request) {
        ExportRequest r = (ExportRequest) request;
        File file = new File(r.file);
        tryExport(file);
    }

    private void tryExport(File file) {
        try {
            exporter.toDisk(repository.getPeople(), file);
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
