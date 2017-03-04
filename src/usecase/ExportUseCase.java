package usecase;

import builderfactory.Request;
import builderfactory.UseCase;
import gateway.ExportImport;
import responder.RefreshResponder;

import java.io.File;
import java.io.IOException;

public class ExportUseCase extends UseCase {
    private final ExportImport exporter;

    public ExportUseCase(ExportImport exporter, RefreshResponder responder) {
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
