package usecase;

import gateway.ExportImport;
import builderfactory.Request;
import builderfactory.UseCase;
import presenter.Presenter;

import java.io.File;
import java.io.IOException;

public class ExportUseCase extends UseCase {
    private final ExportImport exporter;

    public ExportUseCase(ExportImport exporter, Presenter presenter) {
        this.exporter = exporter;
    }

    @Override
    public void execute(Request request) {
        ExportRequest r = (ExportRequest) request;
        tryExport(r.file);
    }

    private void tryExport(File file) {
        try {
            exporter.toDisk(file);
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
