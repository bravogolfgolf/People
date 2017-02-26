package contoller;

import requestor.InputBoundary;
import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCaseFactory;
import responder.Presenter;
import other.View;

import java.util.Map;

public class ImportController {
    private final Request request;
    private final InputBoundary useCase;
    private final Presenter presenter;
    private final View view;

    public ImportController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, Presenter presenter, View view) {
        this.request = request.make("ImportRequest", args);
        this.useCase = useCase.make("ImportUseCase", presenter);
        this.presenter = presenter;
        this.view = view;
    }

    public Object execute() {
        useCase.execute(request);
        return null;
    }
}
