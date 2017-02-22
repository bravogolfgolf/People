package ui;

import domain.Request;
import domain.InputBoundary;

import java.util.Map;

public class ExportController implements Controller {

    private final Request request;
    private final InputBoundary useCase;

    public ExportController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase) {
        this.request = request.make("ExportRequest", args);
        this.useCase = useCase.make("ExportUseCase");
    }

    @Override
    public void execute() {
        useCase.execute(request);
    }
}
