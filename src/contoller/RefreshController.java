package contoller;

import other.Controller;
import other.View;
import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCaseFactory;
import responder.Presenter;
import usecase.refresh.RefreshUseCase;

import java.util.Map;

public class RefreshController implements Controller {
    private final Request request;
    private final RefreshUseCase useCase;
    private final Presenter presenter;
    private final View view;

    public RefreshController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, Presenter presenter, View view) {
        this.request = request.make("RefreshRequest", args);
        this.useCase = (RefreshUseCase) useCase.make("RefreshUseCase", presenter);
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return (view != null && presenter != null) ? view.generateView(presenter.getViewModel()) : null;
    }
}
