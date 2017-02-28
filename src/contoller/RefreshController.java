package contoller;

import other.Controller;
import other.View;
import requestor.Request;
import requestor.RequestBuilderImpl;
import requestor.UseCase;
import requestor.UseCaseFactory;
import responder.Presenter;

import java.util.Map;

public class RefreshController implements Controller {
    private final Request request;
    private final UseCase useCase;
    private final Presenter presenter;
    private final View view;

    public RefreshController(RequestBuilderImpl request, Map<Integer, Object> args, UseCaseFactory useCase, Presenter presenter, View view) {
        this.request = request.get("RefreshRequest", args);
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
