package controller;

import builderfactory.*;
import presenter.Presenter;
import presenter.View;

import java.util.Map;

public class ExportController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final Presenter presenter;
    private final View view;

    public ExportController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, Presenter presenter, View view) {
        this.request = request.make("Export", args);
        this.useCase = useCase.make("Export", presenter);
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return 1;
    }
}