package ui;

import domain.Request;
import domain.InputBoundary;

import java.util.Map;

public class AddPersonController implements Controller {

    private final Request request;
    private final InputBoundary useCase;

    public AddPersonController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase) {
        this.request = request.make("AddPersonRequest", args);
        this.useCase = useCase.make("AddPersonUseCase");
    }

    @Override
    public void execute() {
        useCase.execute(request);
    }
}
