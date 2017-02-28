package contoller;

import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCase;
import requestor.UseCaseFactory;
import responder.Controller;
import responder.Presenter;
import responder.View;

import java.util.Map;

public class RefreshController implements Controller {
    private final Request request;
    private final UseCase useCase;
    private final Presenter presenter;
    private final View view;

    public RefreshController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, Presenter presenter, View view) {
        this.request = request.make("RefreshRequest", args);
        this.useCase = useCase.make("RefreshUseCase");
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return (view != null && presenter != null) ? view.generateView(presenter.getViewModel()) : null;
    }
}
