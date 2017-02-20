package ui;

import domain.Request;
import domain.UseCase;

import java.util.Map;

public class RefreshController implements Controller {
    private final Request request;
    private final UseCase useCase;

    public RefreshController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase) {
        this.request = request.make("RefreshRequest", args);
        this.useCase = useCase.make("RefreshUseCase");
    }

    @Override
    public void execute() {
        useCase.execute(request);
    }
}
