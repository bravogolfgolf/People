package ui;

import java.util.Map;

public class ExportController implements Controller {

    private final Request request;
    private final UseCase useCase;

    public ExportController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase) {
        this.request = request.make("ExportRequest", args);
        this.useCase = useCase.make("ExportUseCase");
    }

    @Override
    public void execute() {
        useCase.execute(request);
    }
}
