package ui;

import domain.Request;
import domain.InputBoundary;

import java.util.Map;

public class DeletePersonController implements Controller {

    private final Request request;
    private final InputBoundary useCase;

    public DeletePersonController(RequestBuilder builder, Map<Integer, Object> args, UseCaseFactory factory) {
        this.request = builder.make("DeletePersonRequest", args);
        this.useCase = factory.make("DeletePersonUseCase");
    }

    @Override
    public void execute() {
        useCase.execute(request);
    }
}
