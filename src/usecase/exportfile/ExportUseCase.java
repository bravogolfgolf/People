package usecase.exportfile;

import exportimportgateway.Export;
import requestor.Request;
import requestor.UseCase;
import responder.Presenter;

import java.io.File;
import java.io.IOException;

public class ExportUseCase extends UseCase {
    private final Export exporter;

    public ExportUseCase(Export exporter, Presenter presenter) {
        this.exporter = exporter;
    }

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

    public class ExportFailed extends RuntimeException {
        ExportFailed(IOException e) {
            super(e);
        }
    }
}
