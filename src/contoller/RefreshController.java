package contoller;

import requestor.InputBoundary;
import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCaseFactory;
import responder.Presenter;
import view.View;

import java.util.Map;

public class RefreshController {
    private final Request request;
    private final InputBoundary useCase;
    private final Presenter presenter;
    private final View view;

    public RefreshController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, Presenter presenter, View view) {
        this.request = request.make("RefreshRequest", args);
        this.useCase = useCase.make("RefreshUseCase", presenter);
        this.presenter = presenter;
        this.view = view;
    }

    public Object execute() {
        useCase.execute(request);
        return (view != null && presenter != null) ? view.generateView(presenter.getViewModel()) : null;
    }
}
