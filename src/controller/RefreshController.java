package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.RequestBuilder;
import builderfactory.UseCase;
import builderfactory.UseCaseFactory;
import presenter.Presenter;
import view.View;

import java.util.Map;

public class RefreshController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final Presenter presenter;
    private final View view;

    public RefreshController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, Presenter presenter, View view) {
        this.request = request.make("Refresh", args);
        this.useCase = useCase.make("Refresh", presenter);
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return (view != null && presenter != null) ? view.generateView(presenter.getViewModel()) : null;
    }
}
