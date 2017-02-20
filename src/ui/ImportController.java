package ui;

import java.util.Map;

public class ImportController implements Controller {

    private final Request request;
    private final UseCase useCase;

    public ImportController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase) {
        this.request = request.make("ImportRequest", args);
        this.useCase = useCase.make("ImportUseCase");
    }

    @Override
    public void execute() {
        useCase.execute(request);
    }
}
