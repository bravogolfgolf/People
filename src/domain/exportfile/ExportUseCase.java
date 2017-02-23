package domain.exportfile;

import domain.Request;
import domain.InputBoundary;

import java.io.File;
import java.io.IOException;

public class ExportUseCase implements InputBoundary {
    private final ExportGateway repository;
    private final Export exporter;
    private final InputBoundary refreshUseCase;

    public ExportUseCase(ExportGateway repository, Export exporter, InputBoundary refreshUseCase) {
        this.repository = repository;
        this.exporter = exporter;
        this.refreshUseCase = refreshUseCase;
    }

    @Override
    public void execute(Request request) {
        ExportRequest r = (ExportRequest) request;
        tryExport(r.file);
        refreshUseCase.execute(request);
    }

    private void tryExport(File file) {
        try {
            exporter.toDisk(repository.getPeople(), file);
        } catch (IOException e) {
            throw new ExportFailed(e);
        }
    }

    public class ExportFailed extends RuntimeException {
        ExportFailed(IOException e) {
            super(e);
        }
    }
}
