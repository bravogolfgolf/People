package controller;

import builderfactory.*;
import responder.RefreshResponder;
import responder.View;

import java.util.Map;

public class ImportController extends Controller {
    private final Request request;
    private final UseCase useCase;
    private final RefreshResponder presenter;
    private final View view;

    public ImportController(RequestBuilder request, Map<Integer, Object> args, UseCaseFactory useCase, RefreshResponder presenter, View view) {
        this.request = request.make("Import", args);
        this.useCase = useCase.make("Import", presenter);
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public Object execute() {
        useCase.execute(request);
        return null;
    }
}
