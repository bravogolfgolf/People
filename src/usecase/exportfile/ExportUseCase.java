package usecase.exportfile;

import exportimportgateway.Export;
import requestor.Request;
import requestor.InputBoundary;

import java.io.File;
import java.io.IOException;

public class ExportUseCase implements InputBoundary {
    private final Export exporter;
    private final InputBoundary refreshUseCase;

    public ExportUseCase(Export exporter, InputBoundary refreshUseCase) {
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
