package controller;

import builderfactory.*;
import responder.RefreshResponder;
import responder.View;

import java.util.Map;

public class ExportController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final RefreshResponder presenter;
    private final View view;

    public ExportController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, RefreshResponder presenter, View view) {
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
