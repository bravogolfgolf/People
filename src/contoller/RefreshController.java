package contoller;

import requestor.InputBoundary;
import requestor.Request;
import respondor.Presenter;
import view.RequestBuilder;
import view.View;

import java.util.Map;

public class RefreshController implements Controller {
    private final Request request;
    private final InputBoundary useCase;
    private final Presenter presenter;
    private final View view;

    RefreshController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, Presenter presenter, View view) {
        this.request = request.make("RefreshRequest", args);
        this.useCase = useCase.make("RefreshUseCase", presenter);
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return (view != null && presenter != null) ? view.generateView(presenter.getViewModel()) : null;
    }
}