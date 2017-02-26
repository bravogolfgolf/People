package usecase.exportfile;

import exportimportgateway.Export;
import requestor.InputBoundary;
import requestor.Request;
import responder.Presenter;

import java.io.File;
import java.io.IOException;

public class ExportUseCase implements InputBoundary {
    private final Export exporter;

    public ExportUseCase(Export exporter, Presenter presenter) {
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

    public class ExportFailed extends RuntimeException {
        ExportFailed(IOException e) {
            super(e);
        }
    }
}
