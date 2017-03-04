package usecase;

import builderfactory.Request;
import builderfactory.UseCase;
import gateway.ExportImport;
import responder.ExportResponder;
import responder.ExportResponse;

import java.io.File;
import java.io.IOException;

public class ExportUseCase extends UseCase {
    private final ExportImport exporter;
    private final ExportResponder responder;

    public ExportUseCase(ExportImport exporter, ExportResponder responder) {
        this.exporter = exporter;
        this.responder = responder;
    }

    @Override
    public void execute(Request request) {
        ExportRequest r = (ExportRequest) request;
        int count = tryExport(r.file);
        ExportResponse response = new ExportUseCaseResponse();
        response.setCount(count);
        responder.present(response);
    }

    private int tryExport(File file) {
        int count;
        try {
            count = exporter.toDisk(file);
        } catch (IOException e) {
            throw new ExportFailed(e);
        }
        return count;
    }

    class ExportFailed extends RuntimeException {
        ExportFailed(IOException e) {
            super(e);
        }
    }
}
